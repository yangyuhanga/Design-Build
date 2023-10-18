package testWeb.vo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectDetectionGUIch extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel imageLabel; // 用于显示图像的标签
    private JLabel typeLabel; // 用于显示物体类型的标签
    private JLabel countLabel; // 用于显示物体数量的标签
    private JLabel positionLabel; // 用于显示物体位置的标签
    private JButton previousButton; // 上一页按钮
    private JButton nextButton; // 下一页按钮

    private List<String> imageFiles; // 存储图像文件名的列表
    private int currentIndex; // 当前图像的索引

    private String predictFolderPath; // 预测结果文件夹路径
    private String labelsFolderPath; // 标签文件夹路径

    public ObjectDetectionGUIch(String predictFolderPath, String labelsFolderPath) {
        this.predictFolderPath = predictFolderPath;
        this.labelsFolderPath = labelsFolderPath;

        initializeGUI(); // 初始化用户界面
        loadImageFiles(); // 加载图像文件列表
        displayCurrentImage(); // 显示当前图像
    }

    private void initializeGUI() {
        // 设置GUI窗口属性
        setTitle("ObjectDetection GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 850);
        setLayout(new BorderLayout());

        // 创建并配置图像标签
        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        // 创建用于显示物体信息的面板
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // 创建用于显示类型、数量和位置的标签
        typeLabel = new JLabel();
        countLabel = new JLabel();
        positionLabel = new JLabel();

        // 创建标签文本的字体
        Font labelTextFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);

        // 添加类型标签
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JLabel typeTextLabel = new JLabel("type: ");
        typeTextLabel.setFont(labelTextFont);
        infoPanel.add(typeTextLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        typeLabel.setFont(labelTextFont);
        typeLabel.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平对齐方式
        infoPanel.add(typeLabel, gbc);

        // 添加数量标签
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel countTextLabel = new JLabel("count: ");
        countTextLabel.setFont(labelTextFont);
        infoPanel.add(countTextLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        countLabel.setFont(labelTextFont);
        countLabel.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平对齐方式
        infoPanel.add(countLabel, gbc);

        // 添加位置标签
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JLabel positionTextLabel = new JLabel("position: ");
        positionTextLabel.setFont(labelTextFont);
        infoPanel.add(positionTextLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        positionLabel.setFont(labelTextFont);
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER); // 设置水平对齐方式
        infoPanel.add(positionLabel, gbc);

        // 为标签应用样式
        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        typeLabel.setForeground(Color.BLUE);
        countLabel.setForeground(Color.RED);

        add(infoPanel, BorderLayout.EAST);

        // 创建包含上一页和下一页按钮的面板
        JPanel buttonPanel = new JPanel(new FlowLayout());
        previousButton = new JButton("上一页");
        nextButton = new JButton("下一页");

        // 为按钮应用样式
        previousButton.setFont(labelFont);
        nextButton.setFont(labelFont);

        // 为按钮添加事件监听器
        previousButton.addActionListener(this);
        nextButton.addActionListener(this);

        // 设置按钮之间的水平间距
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 50)); // 增加水平间距到 20 像素

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 设置窗口位置居中
        setLocationRelativeTo(null);
    }

    private void loadImageFiles() {
        // 从预测文件夹加载图像文件
        File folder = new File(predictFolderPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));

        if (files != null) {
            // 将图像文件名存储在列表中
            imageFiles = new ArrayList<>();
            Arrays.stream(files).forEach(file -> imageFiles.add(file.getName()));
            currentIndex = 0;
        }
    }

    private void displayCurrentImage() {
        // 显示当前图像及其物体信息

        // 获取当前图像文件名和路径
        String imageName = imageFiles.get(currentIndex);
        String imagePath = predictFolderPath + File.separator + imageName;

        // 加载并缩放图像
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));

        // 获取标签文件路径
        String labelFileName = imageName.replaceFirst("[.][^.]+$", "") + ".txt";
        String labelPath = labelsFolderPath + File.separator + labelFileName;
        File labelFile = new File(labelPath);

        if (!labelFile.exists()) {
            // 标签文件不存在，将所有值设置为0
            typeLabel.setText("<html>" + "未知" + "</html>");
            countLabel.setText("<html>" + "0" + "</html>");
            positionLabel.setText("<html>" + "x=0.00, y=0.00, width=0.00, height=0.00" + "</html>");
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(labelFile))) {
                Map<String, Integer> objectCountMap = new HashMap<>();
                StringBuilder typeText = new StringBuilder();
                StringBuilder positionText = new StringBuilder();
                int totalObjectCount = 0;
                boolean hasRecognizedObjects = false;

                String line;
                while ((line = br.readLine()) != null) {
                    String[] labelData = line.split(" ");

                    if (labelData.length >= 5) {
                        // 处理标签数据
                        String objectType = labelData[0];
                        double x = Double.parseDouble(labelData[1]);
                        double y = Double.parseDouble(labelData[2]);
                        double width = Double.parseDouble(labelData[3]);
                        double height = Double.parseDouble(labelData[4]);

                        String objectKey = objectType + "_" + x + "_" + y + "_" + width + "_" + height;
                        if (objectCountMap.containsKey(objectKey)) {
                            int count = objectCountMap.get(objectKey);
                            objectCountMap.put(objectKey, count + 1);
                        } else {
                            objectCountMap.put(objectKey, 1);
                        }
                        hasRecognizedObjects = true;
                    }
                }

                if (hasRecognizedObjects) {
                    // 处理识别到的物体并更新标签
                    for (Map.Entry<String, Integer> entry : objectCountMap.entrySet()) {
                        String objectKey = entry.getKey();
                        String[] keyParts = objectKey.split("_");
                        String objectType = keyParts[0];
                        double x = Double.parseDouble(keyParts[1]);
                        double y = Double.parseDouble(keyParts[2]);
                        double width = Double.parseDouble(keyParts[3]);
                        double height = Double.parseDouble(keyParts[4]);

                        String type = getTypeName(objectType);
                        if (!typeText.toString().contains(type)) {
                            typeText.append(type).append("<br>");
                        }
                        totalObjectCount += entry.getValue();

                        String position = String.format("x=%.2f, y=%.2f, width=%.2f, height=%.2f", x, y, width, height);
                        positionText.append(position).append("<br>");
                    }
                } else {
                    typeText.append("unknown");
                }

                // 更新类型、数量和位置标签
                typeLabel.setText("<html>" + typeText.toString().trim() + "</html>");
                countLabel.setText("<html> " + totalObjectCount + "</html>");
                positionLabel.setText("<html>" + positionText.toString().trim() + "</html>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getTypeName(String objectType) {
        // 根据物体类型代码获取物体类型名称

        switch (objectType) {
            case "0":
                return "book";
            case "1":
                return "key";
            case "2":
                return "cube";
            default:
                return "NULL";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 处理按钮点击事件

        if (e.getSource() == previousButton) {
            if (currentIndex > 0) {
                // 显示上一页图像
                currentIndex--;
                displayCurrentImage();
            }
        } else if (e.getSource() == nextButton) {
            if (currentIndex < imageFiles.size() - 1) {
                // 显示下一页图像
                currentIndex++;
                displayCurrentImage();
            }
        }
    }

    public static void main(String[] args) {
        // 设置预测和标签文件夹的路径
        String predictFolderPath = "E:\\xxq\\runs\\detect\\predict9";
        String labelsFolderPath = "E:\\xxq\\runs\\detect\\predict9\\labels";

        // 创建并显示 ObjectDetectionGUI 窗口
        SwingUtilities.invokeLater(() -> new ObjectDetectionGUIch(predictFolderPath, labelsFolderPath).setVisible(true));
    }
}
