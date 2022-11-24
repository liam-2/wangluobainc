package com.test;

public class OuterClass {

    private  String outerName;

    public void display(){
        System.out.println("outerClass display");
        System.out.println(outerName);

        class InnerClass2{
            private String innerName;
            public void display(){
                System.out.println("InnerClass2 display");
                System.out.println(innerName);
            }
            public InnerClass2(){
                innerName="InnerClass2";
            }
        }
        InnerClass2 innerClass2 = new InnerClass2();
        innerClass2.display();


    }

     public class InnerClass{
        private String innerName;
         public void display(){
             System.out.println("InnerClass display");
             System.out.println(innerName);
         }

         public InnerClass(){
             innerName="InnerClass";
         }
     }

    public static class InnerClass3{
        private String innerName;
        public void display(){
            System.out.println("InnerClass3 display");
            System.out.println(innerName);
        }

        public InnerClass3(){
            innerName="InnerClass3";
        }
    }

    public void setInnerClass3innerName(String name){
        new InnerClass3().innerName=name;
    }





    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        outerClass.display();
        OuterClass.InnerClass innerClass=outerClass.new InnerClass();
        innerClass.display();
        InnerClass3 innerClass3 = new InnerClass3();
        outerClass.setInnerClass3innerName("9999999999999");
        innerClass3.display();

        MyInterface myInterface=new MyInterface() {
            @Override
            public void test() {
                System.out.println("InnerClass4 without name");
            }
        };
        myInterface.test();

    }

}
