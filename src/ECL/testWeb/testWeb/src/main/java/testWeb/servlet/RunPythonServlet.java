package testWeb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RunPythonServlet {

    public static void main(String[] args) {
        try {
            // 设置Conda环境名称
            String condaEnvName = "python310";
            
            // 构建执行Python脚本的命令
            List<String> command = new ArrayList<>();
            command.add("conda");
            command.add("run");
            command.add("-n");
            command.add(condaEnvName);
            command.add("python");
            command.add("E://xxq//111.py"); // 你的Python脚本路径
            
            // 创建进程构建器
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            
            // 启动进程并等待执行完成
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            
            // 处理执行结果
            if (exitCode == 0) {
                System.out.println("Python脚本执行成功！");
            } else {
                System.out.println("Python脚本执行失败！");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
