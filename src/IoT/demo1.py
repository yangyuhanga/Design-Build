from ultralytics import YOLO
import cv2

# Load a model
model = YOLO("E:/xxq/bestx.pt")  # load a pretrained model (recommended for training)

# Define the output directory
output_dir = "E:/xxq"  # Replace this with your desired output directory

# Use the model
img_path = "E:\桌面\小车程序\视频传输模块\PC端\pythonProject"
results = model.predict(
    img_path,
    save=True,
    save_txt=True,
    classes=[0, 1, 2],
    conf=0.5,
    save_dir=output_dir  # Specify the output directory
)

# Save detection results to a custom folder
# The results will be saved in the "output_dir" you specified
