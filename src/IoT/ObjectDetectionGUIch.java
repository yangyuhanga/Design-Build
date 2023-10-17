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
    private JLabel imageLabel;
    private JLabel typeLabel;
    private JLabel countLabel;
    private JLabel positionLabel;
    private JButton previousButton;
    private JButton nextButton;

    private List<String> imageFiles;
    private int currentIndex;

    private String predictFolderPath;
    private String labelsFolderPath;

    public ObjectDetectionGUIch(String predictFolderPath, String labelsFolderPath) {
        this.predictFolderPath = predictFolderPath;
        this.labelsFolderPath = labelsFolderPath;

        initializeGUI();
        loadImageFiles();
        displayCurrentImage();
    }

    private void initializeGUI() {

        setTitle("ObjectDetection GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 850);
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        typeLabel = new JLabel();
        countLabel = new JLabel();
        positionLabel = new JLabel();

        Font labelTextFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);

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
        typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(typeLabel, gbc);

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
        countLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(countLabel, gbc);

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
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(positionLabel, gbc);

        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        typeLabel.setForeground(Color.BLUE);
        countLabel.setForeground(Color.RED);

        add(infoPanel, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        previousButton = new JButton("previous");
        nextButton = new JButton("next");

        previousButton.setFont(labelFont);
        nextButton.setFont(labelFont);

        previousButton.addActionListener(this);
        nextButton.addActionListener(this);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 50));

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void loadImageFiles() {

        File folder = new File(predictFolderPath);
        File[] files = folder
                .listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));

        if (files != null) {

            imageFiles = new ArrayList<>();
            Arrays.stream(files).forEach(file -> imageFiles.add(file.getName()));
            currentIndex = 0;
        }
    }

    private void displayCurrentImage() {

        String imageName = imageFiles.get(currentIndex);
        String imagePath = predictFolderPath + File.separator + imageName;

        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));

        String labelFileName = imageName.replaceFirst("[.][^.]+$", "") + ".txt";
        String labelPath = labelsFolderPath + File.separator + labelFileName;
        File labelFile = new File(labelPath);

        if (!labelFile.exists()) {

            typeLabel.setText("<html>" + "unknown" + "</html>");
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

                typeLabel.setText("<html>" + typeText.toString().trim() + "</html>");
                countLabel.setText("<html> " + totalObjectCount + "</html>");
                positionLabel.setText("<html>" + positionText.toString().trim() + "</html>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getTypeName(String objectType) {

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

        if (e.getSource() == previousButton) {
            if (currentIndex > 0) {

                currentIndex--;
                displayCurrentImage();
            }
        } else if (e.getSource() == nextButton) {
            if (currentIndex < imageFiles.size() - 1) {

                currentIndex++;
                displayCurrentImage();
            }
        }
    }

    public static void main(String[] args) {

        String predictFolderPath = "E:\\xxq\\runs\\detect\\predict9";
        String labelsFolderPath = "E:\\xxq\\runs\\detect\\predict9\\labels";

        SwingUtilities
                .invokeLater(() -> new ObjectDetectionGUIch(predictFolderPath, labelsFolderPath).setVisible(true));
    }
}
