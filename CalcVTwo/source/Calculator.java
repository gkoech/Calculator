package source;

import java.util.*;

import source.structures.*;

public class Calculator{

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        Calculator calc = new Calculator();
        System.out.println("Enter something");
        String str = scan.next();
        str = str + scan.nextLine();
        boolean validExpression = calc.validExpression(str);
        if(validExpression == false){
            while(validExpression == false){
                System.out.println("Try again");
                str = scan.next();
                str = str + scan.nextLine();
                validExpression = calc.validExpression(str);
            }
        }
        str = calc.tidyUp(str);
        QueList<Term> queOfTerms = new QueList<Term>();
        int start = 0;
        double theAnswer = 0.0;
        String tempStr = "";
        long startTime = System.currentTimeMillis();
        while((!str.equals("end")) && (System.currentTimeMillis() - startTime <= 180000)){ // three minutes
            if(calc.isSign(str.charAt(0)) == false){
                theAnswer = 0.0;
                tempStr = "";
                start = 0;
            }
            else if(calc.isSign(str.charAt(0)) == true){
                tempStr = Double.toString(theAnswer);
                start = 1;
            }
            str = tempStr + str;
            queOfTerms = calc.interpretExpression(str, calc.parenIndexes(str));
            theAnswer = calc.exe(queOfTerms, start, theAnswer);
            start = 1;
            queOfTerms.reset();
            System.out.println(theAnswer);
            str = scan.next();
            str = str + scan.nextLine();
            str = calc.tidyUp(str);
            validExpression = false;
            str = calc.tidyUp(str);
        }
        scan.close();
    }

    private String tidyUp(String str){
        String theAnswer = str;
        int[] parens = parenIndexes(str);
        if(parens.length > 0){
            int pIndx = 0;
            theAnswer = "";
            for (int curIndx = 0; curIndx < str.length(); curIndx = curIndx + 1) {
                if (curIndx == parens[pIndx]) {
                    if( (this.openingBrac(str.charAt(curIndx))) && (Character.isDigit(str.charAt(curIndx - 1)) == true)){
                        theAnswer = theAnswer + "*";
                    }
                    else if((this.closingBrac(str.charAt(curIndx))) && (Character.isDigit(str.charAt(curIndx + 1)))){
                        theAnswer = theAnswer + "*" + Character.toString(str.charAt(curIndx));
                    }
                } 
                
                else {
                    theAnswer = theAnswer + Character.toString(str.charAt(curIndx));
                }
            }
        }
        return theAnswer;
    }


    public boolean openingBrac(char c){
        boolean theAnswer = false;
        if(c == '('){
            theAnswer = true;
        }
        return theAnswer;
    }

    public boolean closingBrac(char c){
        boolean theAnswer = false;
        if(c == ')'){
            theAnswer = true;
        }
        return theAnswer;
    }

    public boolean isSign(char c){
        boolean theAnswer = false;
        if((c == '+') || (c == '-')){
            theAnswer = true;
        }
        return theAnswer;
    }

    public boolean isMultOrDiv(char c){
        boolean theAnswer = false;
        if((c == '*') || (c == '/')){
            theAnswer = true;
        }
        return theAnswer;
    }

    public double exe(QueList<Term> que, int index, double theAnswer){
        if(index == que.size() - 1){
            theAnswer = theAnswer + que.getAt(index).getDouble();
            return theAnswer;
        }
        else{
            return exe(que, index + 1, theAnswer + que.getAt(index).getDouble());
        }
    }

    public boolean validExpression(String input){
        boolean theAnswer = true;
        boolean validParens = true;
        boolean letters = this.containsLetters(input);
        if((input.contains("("))|| (input.contains(")"))){
            validParens = this.validParens(input);
        }
        if((validParens == false) || (letters == true)){
            theAnswer = false;
        }
        return theAnswer;
    }

    private boolean validParens(String input){
        boolean theAnswer = false;
        int openCount = 0;
        int closingCount = 0;
        for(int curIndx = 0; curIndx < input.length(); curIndx = curIndx + 1){
            if(input.charAt(curIndx) == '('){
                openCount =+1;
            }
            if(input.charAt(curIndx) == ')'){
                closingCount =+1;
            }
        }
        if(openCount == closingCount){
            theAnswer = true;
        }
        else if(openCount > closingCount ){
            System.out.println("There is a ')' missing");
        }
        else {
            System.out.println("There is a '(' missing");
        }
        return theAnswer;
    }

    private int parenCount(String expression){
        int theAnswer = 0;
        int openCount = 0;
        int closingCount = 0;
        for (int curIndx = 0; curIndx < expression.length(); curIndx = curIndx + 1) {
            if (expression.charAt(curIndx) == '(') {
                openCount = +1;
            }
            if (expression.charAt(curIndx) == ')') {
                closingCount = +1;
            }
        }
        theAnswer = openCount + closingCount;
        return theAnswer;
    }

    private int[] parenIndexes(String string){
        int[] theAnswer = new int[this.parenCount(string)];
        int thisIndx = 0;
        for(int curIndx = 0; curIndx < string.length(); curIndx = curIndx + 1){
            if((string.charAt(curIndx) == '(') || (string.charAt(curIndx) == ')')){
                theAnswer[thisIndx] = curIndx;
                thisIndx = thisIndx + 1;
            }
        }
        return theAnswer;
    }

    private boolean containsLetters(String input) {
        boolean theAnswer = false;
        input = input.toLowerCase();
        for (int curIndx = 0; curIndx < input.length(); curIndx = curIndx + 1) {
            char curChar = input.charAt(curIndx);
            if((curChar == 'p') && (input.charAt(curIndx + 1) == 'i')){
                continue;
            }

            else if((curChar == 'i') && (input.charAt(curIndx - 1) == 'p')){
                continue;
            }

            else if(curChar == 'e'){
                continue;
            }
            
            else if (Character.isLetter(curChar)) {
                theAnswer = true;
                for (int i = 0; i < curIndx; i = i + 1) {
                    System.out.print(" ");
                    if(i == curIndx - 1){
                        System.out.println("^");
                    }
                }
                System.out.println("Error! letters cannot be used at this time.");
                theAnswer = true;
                break;
            }
        }
        return theAnswer;
    }

    /**
     * 
     * Takes an expression and breaks down the expression into terms.
     * This methods assumes that the expression passed in is valid.
     * @param expression - the expression to be taken in, as a string.
     * @param parenCount - the number of parentheses in the expression.
     * @return a que with each term that was in the expression.
     */
	public QueList<Term> interpretExpression(String expression, int[] parenIndexes){
        QueList<Term> theAnswer = new QueList<Term>();
        Term thisTerm = new Term();
        int curIndx = 0;
        String str = "";
        if(parenIndexes.length > 1){
            int parensToRev = 0;
            int thisParenIndex  = 0;
            while(curIndx < parenIndexes[parenIndexes.length - 1]){
                if(curIndx == parenIndexes[thisParenIndex]){
                    if(expression.charAt(parenIndexes[thisParenIndex]) == '('){
                        str = str + Character.toString(expression.charAt(curIndx));
                        parensToRev = parensToRev + 1;
                    }
                    else if(expression.charAt(parenIndexes[thisParenIndex]) == ')'){
                        str = str + Character.toString(expression.charAt(curIndx));
                        parensToRev = parensToRev - 1;
                        if(parensToRev == 0){
                            thisTerm.setString(str);
                        }
                    }
                    thisParenIndex = thisParenIndex + 1;
                }

                else{
                    if(this.isSign(expression.charAt(curIndx))){
                        if(curIndx == 0){
                            thisTerm.setSign(expression.charAt(curIndx));
                        }

                        else {
                            if(parensToRev == 0){
                                thisTerm.setString(str);
                                theAnswer.push(thisTerm);
                                str = "";
                                thisTerm = new Term();
                                thisTerm.setSign(expression.charAt(curIndx));
                            }
                            else{
                                str = str + Character.toString(expression.charAt(curIndx));
                            }
                        }
                    }
                    else{
                        str = str + Character.toString(expression.charAt(curIndx));
                    }
                }
                curIndx = curIndx + 1;
            }
        }
        else{
            // there are no parentheses.
            while(curIndx < expression.length()){
                if(this.isSign(expression.charAt(curIndx)) == true){
                    thisTerm.setString(str);
                    theAnswer.push(thisTerm);
                    thisTerm = new Term();
                    thisTerm.setSign(expression.charAt(curIndx));
                    str = "";
                }

                else{
                    str = str + Character.toString(expression.charAt(curIndx));
                    if(curIndx == expression.length() - 1){
                        thisTerm.setString(str);
                        theAnswer.push(thisTerm);
                    }
                }
                curIndx = curIndx + 1;
            }
        }
		return theAnswer;
	}
}
