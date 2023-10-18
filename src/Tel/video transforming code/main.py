import socket
import cv2
import io
from PIL import Image
import numpy as np

f = open('1.txt', 'w')
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, 0)
s.bind(("0.0.0.0", 9090))
imgfile1="1.png"
imgfile2="2.png"
imgfile3="3.png"
pngnum=1
STR=""
STR1="SPEED: "
STR2=" DISTANCE: "
STR3=" DIRECTION: "
STR4=" TIME: "
def ByteToHex( bins ):
    return ''.join( [ "%02X" % x for x in bins ] ).strip()

while True:
    data, IP = s.recvfrom(100000)
    if data[0]==255:
        tx=data
        bytes_stream = io.BytesIO(tx)
        image = Image.open(bytes_stream)
        img = np.asarray(image)
        img = cv2.cvtColor(img, cv2.COLOR_RGB2BGR)  # ESP32采集的是RGB格式，要转换为BGR（opencv的格式）
        cv2.imshow("ESP32 Capture Image", img)
    else:
        #print(data)
        if data[0]==165:
            x=data[6:].decode()
            print(len(x),x)
            minute=x[1]
            second='0'
            if len(x)==5:
                b=int(x[4],16)
                b=str(b)
                second=b
            elif len(x)==6:
                b1=int(x[4],16)
                b2=int(x[5],16)
                b=b1*16+b2
                b=str(b)
                second=b
            #print(data[0],data[1],data[2],data[3],data[4],data[5],minute,second)
            if data[3]==1:
                dr='go ahead'
                STR = STR1 + str(data[1])
                STR += STR2 + str(data[2])
                STR += STR3 + dr
                STR += STR4 + minute + " m " + second + " s\n"
            elif data[3]==2:
                dr='turn_right'
                STR = STR3 + dr
                STR += STR4 + minute + " m " + second + " s\n"
            elif data[3]==4:
                dr='turn_left'
                STR = STR3 + dr
                STR += STR4 + minute + " m " + second + " s\n"
            f.write(STR)
            print(STR)
    if cv2.waitKey(1) == ord("s"):
        print("拍照成功")
        if pngnum==1:
            cv2.imwrite(imgfile1,img)
            pngnum+=1
        elif pngnum==2:
            cv2.imwrite(imgfile2, img)
            pngnum+=1
        elif pngnum==3:
            cv2.imwrite(imgfile3, img)
            pngnum=1
    if cv2.waitKey(1) == ord("q"):
        f.close()  # 当文件结束使用后记住需要关闭文件
        break