<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of connexion
 *
 * @author hp
 */
class connexion {

    private $connextion;

    public function __construct() {
        $host = 'localhost';
        $dbname = 'school';
        $login = 'root';
        $password = '';
         try{
        $this->connextion = new PDO("mysql:host=$host;dbname=$dbname", $login, $password);
        $this->connextion->query("SET NAMES UTF8");
        } catch (Exception $e) {
            die('Erreur : ' . $e->getMessage());
        }
    }

    function getConnextion() {
        return $this->connextion;
    }

}
?>

