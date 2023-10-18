import os
import subprocess

# 替换为您的Conda环境的名称
conda_env_name = 'python310'

# 激活Conda环境
activate_env_command = f'conda activate {conda_env_name}'
subprocess.Popen(activate_env_command, shell=True).wait()

# 检查环境是否成功激活
activate_env_status = os.system(activate_env_command)
if activate_env_status != 0:
    print(f"Failed to activate Conda environment: {conda_env_name}")
    exit(1)

# 切换到特定文件夹
os.chdir('E://xxq')

# 执行Python脚本
try:
    subprocess.check_call(['python', "demo1.py"])
except subprocess.CalledProcessError as e:
    print(f"Error executing Python script: {e}")
