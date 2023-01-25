#include <Servo.h>

Servo servo1;
Servo servo2;
Servo servo3;
const int servo1Pin = 9;
const int servo2Pin = 10;
const int servo3Pin = 11;
char cmd;

void setup() {
  
  // 시리얼 통신 시작 (boadrate: 9600)
  Serial.begin(9600);
  servo1.attach(servo1Pin);
  servo2.attach(servo2Pin);
  servo3.attach(servo3Pin);
}

void loop() { 

  // 컴퓨터로부터 시리얼 통신이 전송되면, 한줄씩 읽어와서 cmd 변수에 입력
  if(Serial.available()){
    cmd = Serial.read();
    switch(cmd){
      case '1':
        Serial.println("Servo1 0");
        servo1.write(0);
        delay(100);
      break;
      case '2':
        Serial.println("Servo1 90");
        servo1.write(90);
        delay(100);
      break;
      case '3':
        Serial.println("Servo2 0");
        servo2.write(0);
        Serial.println("Servo1 0");
        servo1.write(0);
        delay(100);
      break;
      case '4':
        Serial.println("Servo2 90");
        servo2.write(90);
        Serial.println("Servo1 0");
        servo1.write(0);
        delay(100);
      break;
      case '5':
        Serial.println("Servo3 0");
        servo3.write(0);
        Serial.println("Servo1 0");
        servo1.write(0);
        delay(100);
      break;
      case '6':
        Serial.println("Servo3 90");
        servo3.write(90);
        Serial.println("Servo1 0");
        servo1.write(0);
        delay(100);
      break;
    }
    Serial.println("done!");
  }
}