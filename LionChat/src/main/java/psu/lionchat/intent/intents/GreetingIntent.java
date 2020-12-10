package psu.lionchat.intent.intents;

import psu.lionchat.intent.Intent;

public class GreetingIntent extends Intent {

        public GreetingIntent() {
            super();
            this.intentName = "Greeting Intent";
        }

        @Override
        public String toString(){
            return "GreetingIntent";
        }

}
