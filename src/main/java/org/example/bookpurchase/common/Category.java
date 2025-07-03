package org.example.bookpurchase.common;

public enum Category {
    ALL,
    KIDS,
    COMPUTER,
    SCIENCE,
    THEORY,
    ESSAY,
    HISTORY;


    public String getDisplayName(){
        return switch (this){
            case ALL -> "전체";
            case KIDS ->"아동용";
            case COMPUTER -> "컴퓨터";
            case SCIENCE -> "과학";
            case THEORY -> "소설";
            case ESSAY -> "에세이";
            case HISTORY -> "역사";

        }; //검색할때 쓰는것같음
    }
}
