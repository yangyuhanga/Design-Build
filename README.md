# Design-Build
London Queen Mary University 2021 Design &amp; Build Results
## IoT
### data
<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/8770fb4774df0b1fa465c1133db88a9.png">
</div>

#### Training Data
In order to train an effective object detection model, we collected 3523 images of keys, Rubik's cubes, and books. These images were annotated using the LabelImg software and saved in YOLO format. Additionally, we considered various environmental settings, lighting conditions, and angles. We adjusted these parameters on top of the original dataset to create versions with varying lighting and backgrounds (specific parameter adjustments will be explained later) to enhance training accuracy. The training data is stored in the `data/train` folder.

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/36acd09fe93305593b71b07ade0095e.png">
</div>

#### Validation Data
The validation dataset is separate from the training dataset. We gathered an additional 592 images to serve as our validation set, which helps us assess the model's performance. The validation data is stored in the `data/valid` folder. This data is used to evaluate the model's effectiveness and ensure it generalizes well to new, unseen data.

### Model Training
Throughout the process, we trained a total of three models, named best1.pt, best2.pt, and best3.pt. Among these models, best3.pt showed the best performance. All these models are saved in the 'model' folder.

#### best1.pt
- We used yolov8n.pt as the base model and trained it for 300 epochs on the data from the 'train' dataset.

#### best2.pt
- We used yolov8x.pt as the base model and trained it for 300 epochs on the data from the 'train' dataset.

#### best3.pt
- We used best2.pt as the base model and performed fine-tuning to address some of the shortcomings of best2.

Additionally, we evaluated the performance of the best3 model on the 'valid' dataset. Evaluation metrics include accuracy, recall, F1 score, IoU, and more. The final evaluation results are saved in the 'evaluate' folder.

### Model Inference
To perform model inference, follow these steps:

1. Set up the project environment by installing the required dependencies listed in the 'requirements.txt' file.

2. Modify the 'imagepath' value in the 'src/IoT/demo.py' file to specify the folder path containing the images you want to perform inference on.

3. Run the 'src/IoT/inference.py' script.

These steps will enable you to use the trained model to make predictions on the specified images in the designated folder.

### GUI
To achieve a graphical representation of the model inference results, we have designed the 'src/IoT/ObjectDetectionGUIch.java' file. After completing the model inference, simply modify the 'predictFolderPath' and 'labelsFolderPath' in the file to their respective paths.

This will allow you to visualize and display the results of model predictions using the graphical user interface (GUI) provided in the 'ObjectDetectionGUIch.java' file.

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/5391ba07045bbc644c7d9aada6ab005.png">
</div>

### report on the accuracy
Firstly, we use Photoshop to batch process test set images,simulate bright or dark environments
by adjusting brightness and exposure.
For images in bright environments, our parameters are brightness 70 and exposure 0.3.

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/b408ea260c46d51a8df2c7a5fb3eea1.png">
</div>

For images in dark environments, our parameters are brightness -70 and exposure -0.3.

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/80ee78d55d25b39ca9aa2c5ee2e9e63.png">
</div>

We found that when the model is in a dark environment, its recognition accuracy is almost
unaffected, but when it is in a bright environment, its recognition accuracy is slightly affected,
indicating that the model's recognition ability can still be trained and strengthened under bright
conditions. 

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/4b203897ba0a925160e465504fd07e3.png">
</div>


Overall, the model we train can adapt to various different environments.

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/713b45b6c71686cbaa75dc7e59f3327.png">
</div>


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

#### Initial Interface:

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/429072fbbbf3c0b7216c49733fccfda.png">
</div>

Users can click buttons to access the registration and login interfaces.

#### Registration Interface:

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/9f51e88c86a84037786324823dc7098.png">
</div>

New users can complete their registration here. Various restrictions are in place, such as preventing duplicate usernames, with pop-up notifications for errors. (Screenshot)

#### Gender Error Messages:
A: If left blank 

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/f4282646f381b08f8a827297b89ee83.png">
</div>

B: If the input doesn't meet the criteria 

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/5b698733636fa7276f62fc9bb33a19c.png">
</div>

#### Password Error Messages:
A: If left blank 

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/87b7c7a2567ca56e06b8125e22a7be3.png">
</div>

B: If the input doesn't meet the criteria

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/c3a7f0a7e00f9f6dfc77c349770984c.png">
</div>

#### Similar error messages apply for other fields.

#### Login Interface:

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/94d48252183c23c6c3576dfe9a86892.png">
</div>

Registered users can log in here. If login fails, an error message is displayed. (Screenshot)

#### Successful Login Interface:

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/2af3dd8f57d6a86de0c2df4b8199dab.png">
</div>

It displays the user's account and the unique ID for the small car registered to that user. It also provides access to more features of the platform. Clicking "VIEW MORE" takes you to the next interface.

#### Records Interface:

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/f376834457103dc4bc221586bc87d4a.png">
</div>

This page displays data collected after the small car's operation, including record ID, time, speed, distance, and direction. The record ID is auto-incremented and resets on each data read. Clicking the "dataread" button in the top left corner clears the data from the database and re-reads small car data to ensure data updates according to user requirements.

#### Object Detection Photo Display Interface:

<div align="center">
  <img src="https://github.com/yangyuhanga/Design-Build/blob/main/pictures/5391ba07045bbc644c7d9aada6ab005.png">
</div>

After displaying "Detecting Objects..." on this page, a GUI interface will pop up. This is one of our team's innovations. Instead of repeatedly storing and retrieving redundant data in the database, we chose to create and display a GUI interface. This allows users to intuitively view photos, coordinates, and detected treasures from the small car's operation records. This innovation also reflects our group's interactive approach.

### Tel
#### Meeting Notes: Discussion on the Raspberry Pi-based Intelligent Car

---

**Host Li Yanhang:**  
Hello everyone, our goal today is to discuss how to create an intelligent car based on Raspberry Pi. According to the requirements, the car needs to achieve functions such as obstacle avoidance, photography, color recognition, data transmission, and path recognition. I hope every member can provide suggestions and ideas on implementing these features. Let's start with Wan Chengjun.

---

**Wan Chengjun:**  
Thank you, Li Yanhang. First, to implement the obstacle avoidance feature, we can use ultrasonic sensors. Ultrasonic sensors can send out ultrasonic waves and measure the time it takes for them to reflect back. By this time and the speed of the ultrasonic wave, we can calculate the distance between the object and the sensor. If the sensor detects an obstacle in front and the distance is too close, we can program the car to turn to avoid a collision.

For color recognition, we can use an RGB sensor or a camera. The RGB sensor can detect the color of objects, and the camera can capture images and identify colors through image processing algorithms. For example, we can use the OpenCV library to process images captured by the camera and determine the main colors in the image.

---

**Wang Zihe:**  
Thank you, Wan Chengjun. Continuing with the photography feature, it's relatively simple. We can use the Raspberry Pi's camera module. It's a compact 5MP or 8MP camera that can easily connect to the Raspberry Pi. We can write a simple Python script to control the camera to take pictures.

For data transmission, Raspberry Pi already has a built-in WiFi module. We can use this module to send and receive data. For instance, the car can send photos taken to a remote server or the user's mobile phone via WiFi.

---

**Zhao Ran:**  
Thank you, Wang Zihe. Path recognition is a complex but interesting issue. Initially, we can place some colored lines on the ground, such as black tape. Then, we can use the camera to capture images of the ground and use image processing algorithms to detect these lines. When the car detects the lines, it can follow them.

For the challenge of navigating a maze to find treasure, we can use a simple maze-solving algorithm, like the right-hand rule. The principle is, when the car enters a maze, it always tries to follow the wall on its right. This way, the car will eventually find the exit. Of course, this is a basic method, and for more complex mazes, we might need to use more intricate algorithms.

---

**Gao Zihang:**  
Thank you, Zhao Ran. Regarding the suggestion of using ultrasonic sensors to correct the travel path, I think it's an excellent idea. When the car is driving along the path, it might deviate from the course. The ultrasonic sensors can help the car detect its distance from the walls beside it. If the car veers off the path, it can adjust its direction to get back on the correct route.

---

**Host Li Yanhang:**  
Thank you, everyone. We've discussed how to implement all the car's functions. The next step is to start designing and programming. I hope our car can successfully complete the maze task! Let's all do our best!

---


