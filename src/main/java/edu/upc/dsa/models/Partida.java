package edu.upc.dsa.models;

import edu.upc.dsa.CRUD.MYSQL.*;
import edu.upc.dsa.CRUD.util.RandomUtils;
import java.sql.SQLIntegrityConstraintViolationException;
import edu.upc.dsa.CRUD.util.RandomUtils;

public class Partida {

        // Basic attributes
        String idPartida;
        int difficulty;
        int nivel;

        String player;
        String idMapa;

        int points;
        String date;

        // Constructor

        public Partida() {
            this.idPartida = RandomUtils.getId();
        }

        public Partida(int difficulty,String player, String idMapa) {
            this();
            this.idPartida = RandomUtils.getId();
            this.difficulty = difficulty;
            this.nivel = 1;
            this.player = player;
            this.idMapa = idMapa;
            this.points = 0;
            //this.date = RandomUtils.getDate();
        }

        // Getters and setters

        public String getIdPartida() {
            return idPartida;
        }

        public void setIdPartida(String idPartida) {
            this.idPartida = idPartida;
        }

        public int getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(int difficulty) {
            this.difficulty = difficulty;
        }

        public int getNivel() {
            return nivel;
        }

        public void setNivel(int nivel) {
            this.nivel = nivel;
        }

        public String getPlayer() {
            return player;
        }

        public void setPlayer(String player) {
            this.player = player;
        }

        public String getIdMapa() {
            return idMapa;
        }

        public void setIdMapa(String idMapa) {
            this.idMapa = idMapa;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        // toString

        @Override

        public String toString() {
            return "Partida [idPartida=" + idPartida + ", difficulty=" + difficulty + ", nivel=" + nivel + ", player=" + player + ", idMapa=" + idMapa + ", points=" + points + ", date=" + date + "]";
        }

}
