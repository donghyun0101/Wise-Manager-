<?php
    header("Content-Type: text/html;charset=UTF-8");
    $conn = mysqli_connect("localhost","kdonghyun0101","card5648*#!","kdonghyun0101");
    $data_stream = "'".$_POST['wise']."','".$_POST['name']."'";
    $resetPosition = "alter table WiseList auto_increment =1";
    $query = "insert into WiseList(wise,name) values (".$data_stream.")";
    $sql = "select wise, name from WiseList ";
    $R = array(); // 결과 담을 변수 생성

    if(!empty($_POST['wise']) && !empty($_POST['name']))
    {
        $result = mysqli_query($conn, $query);
    }

    $viewSql = mysqli_query($conn,$sql);
    while($row = mysqli_fetch_array($viewSql))
    {
        array_push($R, array("wise"=>$row[0],"name"=>$row[1]));
    }
    echo json_encode(array("wiselist"=>$R));
    
    mysqli_close($conn);
?>
    