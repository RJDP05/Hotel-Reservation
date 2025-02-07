package com.jdbc.hr;

public class User {

        String name;
        int number;
        String room;
        int id;
        

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public int getId(){
            return id;
        }

        public void setId(int id) {this.id = id;}
}
