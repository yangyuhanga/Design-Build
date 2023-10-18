# Design-Build
London Queen Mary University 2021 Design &amp; Build Results
## IoT
### 数据
#### 训练数据
为了训练一个有效的目标检测模型，我们收集了3523张钥匙、魔方和书籍的图像，并在labellmg软件上对他进行了标注，并保存为YOLO格式。同时，我们考虑了各种环境设置、照明条件和角度，在原数据集基础上调整了这些参数，产生了明暗不同背景的版本（具体调整参数会在之后说明），以提高训练精度。训练数据保存在data/train文件夹下
#### 验证数据
验证数据集是独立于训练数据集的，我们又重新搜集了592张图片作为我们的验证集，去判断模型是否完善。验证数据保存在data/valid文件夹下

### 模型训练
我们整个过程一共训练出了三个模型，分别为best1.pt，best2.pt，best3.pt，其中best3模型效果最好，这些模型都保存在model文件夹下。
#### best1.pt
我们以yolov8n.pt为基底模型，在data/train数据集下训练三百轮；
#### best2.pt
我们以yolov8x.pt为基底模型，在data/train数据集下训练三百轮；
#### best3.pt
我们以best2.pt为基底模型，对于best2模型的一些不足之处进行了微调；
同时，我们对best3模型在data/valid数据集上进行评估，评估指标包括准确性、召回率、F1分数和IoU等，最终结果保存在了evaluate文件夹下。

### 模型推理
首先按照requirements.txt文件对项目环境进行部署，
然后修改src/IoT/demo.py文件中的imagepath的值，将其改为要推理照片的文件夹地址，
最后运行src/IoT/inference.py脚本即可。

### GUI
为实现模型推理结果的图像化显示，我们设计了src/IoT/ObjectDetectionGUIch.java文件，
在模型推理完毕后，只需将文件中的predictFolderPath和labelsFolderPath修改为相应的路径即可

### report on the accuracy
Firstly, we use Photoshop to batch process test set images,simulate bright or dark environments
by adjusting brightness and exposure.
For images in bright environments, our parameters are brightness 70 and exposure 0.3.
For images in dark environments, our parameters are brightness -70 and exposure -0.3.
We found that when the model is in a dark environment, its recognition accuracy is almost
unaffected, but when it is in a bright environment, its recognition accuracy is slightly affected,
indicating that the model's recognition ability can still be trained and strengthened under bright
conditions. Overall, the model we train can adapt to various different environments.


## ECL
### Configuration
#### A. Database Configuration:
database.properties

jdbc.url=jdbc:mysql://localhost:3306/java

jdbc.username=root

jdbc.password=root

Please modify the corresponding information in your project to connect to the database correctly, based on your database details.

#### B. Servlet Configuration:
<!-- web.xml -->

<servlet>
  
  <servlet-name>MyServlet</servlet-name>
  
  <servlet-class>com.example.MyServlet</servlet-class>
  
</servlet>

<servlet-mapping>
  
  <servlet-name>MyServlet</servlet-name>
  
  <url-pattern>/myapp/*</url-pattern>
  
</servlet-mapping>

Complete the Servlet configuration in the web.xml file based on your project's servlet file.

#### C. Other Configuration:
When this project was created, it was developed in the following environment:

Development Environment: Eclipse IDE 2023-6

Runtime Environment: Tomcat 9.0, JDK 19, MySQL 8.0

Ensure compatibility with these environments when running the project.

### Database
When this project was created, we used MySQL Workbench for easy data table management. We provide a .sql file that you need to run in your workbench to generate the database.
We used two tables: 'userinfo' to store user registration and login information, and 'record' to store data transmitted by small cars (data processing is applied to the data based on examples, and you can make 
modifications by referring to the Java Web code, specifically in testWeb\src\main\java\testWeb\vo\DataProcessor.java).

### How to Run:
The project folder includes all the files for Tomcat configuration and the project itself. If you are using Eclipse, simply clear your workspace, import the local folder, configure it, and run the project.

### Explanation of Running Results (Including Functionality Description):

#### Initial Interface: (Screenshot)
Users can click buttons to access the registration and login interfaces.

#### Registration Interface: (Screenshot)
New users can complete their registration here. Various restrictions are in place, such as preventing duplicate usernames, with pop-up notifications for errors. (Screenshot)

#### Gender Error Messages:
A: If left blank (Screenshot)
B: If the input doesn't meet the criteria (Screenshot)

#### Password Error Messages:
A: If left blank (Screenshot)
B: If the input doesn't meet the criteria (Screenshot)

#### Similar error messages apply for other fields.

#### Login Interface: (Screenshot)
Registered users can log in here. If login fails, an error message is displayed. (Screenshot)

#### Successful Login Interface: (Screenshot)
It displays the user's account and the unique ID for the small car registered to that user. It also provides access to more features of the platform. Clicking "VIEW MORE" takes you to the next interface.

#### Records Interface: (Screenshot)
This page displays data collected after the small car's operation, including record ID, time, speed, distance, and direction. The record ID is auto-incremented and resets on each data read. Clicking the "dataread" button in the top left corner clears the data from the database and re-reads small car data to ensure data updates according to user requirements.

#### Object Detection Photo Display Interface: (Screenshot)
After displaying "Detecting Objects..." on this page, a GUI interface will pop up. This is one of our team's innovations. Instead of repeatedly storing and retrieving redundant data in the database, we chose to create and display a GUI interface. This allows users to intuitively view photos, coordinates, and detected treasures from the small car's operation records. This innovation also reflects our group's interactive approach.


