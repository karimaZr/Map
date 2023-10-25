<?php

include_once 'dao/IDao.php';
include_once 'beans/Position.php';
include_once 'connexion/Connexion.php';

class PositionService implements IDao {

    private $listPosition = array();
    private $connexion;
    private $position;

    public function __construct() {
        $this->connexion = new Connexion();
        $this->position = new Position("", "", "", "", "");
    }

    public function create($position) {
        $query = "INSERT INTO position (latitude, longitude, date, imei) VALUES (:latitude, :longitude, :date, :imei)";
        $req = $this->connexion->getConnextion()->prepare($query);

        // Bind parameters without passing by reference
        $req->bindParam(':latitude', $position->getLatitude());
        $req->bindParam(':longitude', $position->getLongitude());
        $req->bindParam(':date', $position->getDate());
        $req->bindParam(':imei', $position->getImei());

        // Execute the query
        $req->execute() or die('SQL');
    }

    public function delete($obj) {
        
    }

    public function getAll() {
        $query = "select * from position";
        $req = $this->connexion->getConnextion()->prepare($query);
        $req->execute();
        return $req->fetchAll(PDO::FETCH_ASSOC);
    }

    public function getById($obj) {
        
    }

    public function update($obj) {
        
    }

}
