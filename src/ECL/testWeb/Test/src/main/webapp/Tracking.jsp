<!DOCTYPE html>
<html>
<head>
    <title>Robot Tracking</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <h1>Robot Tracking</h1>
    <div id="map" style="width: 800px; height: 600px;"></div>
    <p>当前速度: <span id="currentSpeed">0</span> m/s</p>
    
    <script>
        // 使用JavaScript实时更新机器人位置和速度
        function updateRobotPositionAndSpeed() {
            // 发送Ajax请求到后端获取实时数据
            $.ajax({
                url: "/robotData",
                method: "GET",
                dataType: "json",
                success: function(data) {
                    // 更新地图上的机器人位置（根据数据来更新圆点位置）
                    // 更新当前速度
                    $("#currentSpeed").text(data.latestData.speed + " m/s");
                    
                    // 处理机器人运动历史数据
                    var historyData = data.movementHistory;
                    // 根据历史数据更新地图上的轨迹
                    // 这部分需要根据地图库来实现
                }
            });
        }
        
        // 定时刷新实时数据
        setInterval(updateRobotPositionAndSpeed, 1000); // 每秒刷新一次
    </script>
</body>
</html>