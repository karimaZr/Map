<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include_once ("service/PositionService.php");
    create();
}

function create() {
    // Check if the required keys exist in the POST data
    
        $latitude = $_POST['latitude'];
        $longitude = $_POST['longitude'];
        $date = $_POST['date'];
        $imei = $_POST['imei'];

        $position = new Position(1, $latitude, $longitude, $date, $imei);
        $ss = new PositionService();
        $ss->create($position);
   
}
