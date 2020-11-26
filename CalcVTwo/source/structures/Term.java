
package source.structures;

import java.util.ArrayList;

public class Term{
    private String valueStr;
    private double valueDouble;
    private char sign;

    public Term(){

    }

    public Term(String str){
        this.valueStr = str;
        this.valueDouble = toDouble(str);
    }

    /**
     * Sets the sign of this term
     * @param c
     *          - what the sign is set to.
     */
    public void setSign(char c){
        if(isSign(c) == false){
            throw new IllegalStateException("The paramter passed in is not a sign");
        }
        this.sign = c;
    }

    /**
     * Gets the sign of this term
     * @return the sign of this term object.
     */
    public char getSign(){
        return this.sign;
    }

    private boolean isSign(char c) {
        boolean theAnswer = false;
        if ((c == '+') || (c == '-') || (c == '*') || (c == '/')) {
            theAnswer = true;
        }
        return theAnswer;
    }

    private double toDouble(String str){
        double theAnswer = 0;
        if((str.contains("/")) || (str.contains("*"))){
            theAnswer = this.converter(str);
        }
        else if((str.equals("pi")) || (str.equals("e"))){
            if (str.equalsIgnoreCase("e")) {
                theAnswer = 0.0;
                for (int thisIndx = 0; thisIndx < Math.pow(2.0, 10); thisIndx = thisIndx + 1) {
                    theAnswer = theAnswer + 1 / this.factorial(thisIndx);
                }
            } 
            
            else if (str.equalsIgnoreCase("pi")) {
                theAnswer = 28.27433388 / 9;
            }
        }
        else{
            theAnswer = Double.parseDouble(str);
        }
        if(this.sign == '-'){
            theAnswer = theAnswer * -1;
        }
        return theAnswer;
    }

    private double converter(String str){
        
        ArrayList<Integer> operationsAt = this.opLocations(str);
        double theAnswer = 0.0;
        theAnswer = Double.parseDouble(str.substring(0, operationsAt.get(0)));
        int curIndx = 0;
        while(curIndx < operationsAt.size()){
            if(curIndx == operationsAt.size() - 1){
                if (str.charAt(operationsAt.get(curIndx)) == '*') {
                    String val = str.substring(operationsAt.get(curIndx) + 1, str.length());
                    theAnswer = theAnswer * Double.parseDouble(val);
                } 
                
                else if (str.charAt(operationsAt.get(curIndx)) == '/') {
                    String val = str.substring(operationsAt.get(curIndx) + 1, str.length());
                    theAnswer = theAnswer / Double.parseDouble(val);
                }
            }

            else {
                if (operationsAt.get(curIndx) == '*') {
                    theAnswer = theAnswer * Double.parseDouble(str.substring(curIndx, operationsAt.get(curIndx + 1)));
                } 
                
                else if (operationsAt.get(curIndx) == '/') {
                    theAnswer = theAnswer / Double.parseDouble(str.substring(curIndx, operationsAt.get(curIndx + 1)));
                }
            }
            curIndx = curIndx + 1;
        }
        return theAnswer;
    }

    private double factorial(double curIndx){
        if(curIndx == 0){
            return 1;
        }
        else return curIndx * factorial(curIndx - 1);
    }
    private ArrayList<Integer> opLocations(String s){
        ArrayList<Integer> theAnswer = new ArrayList<Integer>();
        for(int curIndx = 0; curIndx < s.length(); curIndx = curIndx + 1){
            if((s.charAt(curIndx) == '*') || (s.charAt(curIndx) == '/')){
                theAnswer.add(curIndx);
            }
        }
        return theAnswer;
    }

    /**
     * Gets the value of this term as a double
     * @return the term's double value.
     */
    public double getDouble(){
        return this.valueDouble;
    }

    public void setString(String s){
        String str = s;
        if((s.contains("*")) || (s.contains("/"))){
            str = "";
            ArrayList<Integer> operations = opLocations(s);
            QueList<String> strings = new QueList<String>();
            int curIndx = 0;
            int opIndx = 0;
            while(curIndx < s.length()){
                if(curIndx == operations.get(opIndx)){
                    strings.push(str);
                    str = "";
                    if(opIndx == operations.size() - 1){
                        str = s.substring(operations.get(opIndx) + 1, s.length());
                        strings.push(str);
                        break;
                    }
                    opIndx = opIndx + 1;
                }
                else{
                    str = str + Character.toString(s.charAt(curIndx));
                }
                curIndx = curIndx + 1;
            }
            curIndx = 0;
            opIndx = 0;
            double dAns = 1;
            str = "";
            while(curIndx < strings.size()){
                if(curIndx == 0){
                    str = strings.getAt(curIndx);
                    String thisStr1 = strings.getAt(curIndx + 1);
                    if(s.charAt(operations.get(opIndx)) == '*'){
                        dAns = Double.parseDouble(str) * Double.parseDouble(thisStr1);
                    }
                    else if (s.charAt(operations.get(opIndx)) == '/') {
                        dAns = Double.parseDouble(str) / Double.parseDouble(thisStr1);
                    }
                    curIndx = curIndx + 1;
                    opIndx = opIndx + 1;
                }

                else{
                    str = strings.getAt(curIndx);
                    if(s.charAt(operations.get(opIndx)) == '*'){
                        dAns = dAns * Double.parseDouble(strings.getAt(curIndx));
                    }
                    else if(s.charAt(operations.get(opIndx)) == '/'){
                        dAns = dAns / Double.parseDouble(strings.getAt(curIndx));
                    }
                }
                curIndx = curIndx + 1;
            }
            s = Double.toString(dAns);
            this.valueDouble = toDouble(s);
        }
        else{
            this.valueDouble = toDouble(s);
        }
        this.valueStr = s;
    }

    public String getString(){
        return this.valueStr;
    }
}
