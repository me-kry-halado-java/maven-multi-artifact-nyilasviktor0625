package hu.meiit.haladojava;

import hu.meiit.haladojava.logic.Executor;
import hu.meiit.haladojava.logic.Expression;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public enum Mathop
    {
        PLUS("+"),
        MINUS("-"),
        DIV("/"),
        MULTIP("*");
        private final String operator ;
        Mathop(String operator) {
            this.operator=operator;
        }
        public String getOperator()
        {
            return this.operator;
        }
        public static Mathop getMathopName(String st) {
            for (Mathop operator : Mathop.values()) {
                if (st.equals(operator.getOperator())) {
                    return operator;
                }
            }
            return null;
        }

    };



    private static Mathop currentop;

    private static final String errorResponse = "-";
    private static Mathop operator;
    public static String getErrorResponse() {
        return errorResponse;
    }

    public static String [] parse(String expression){
        String[] returnValue = new String[0];
        if(expression.length()>3) {
            returnValue = expression.split(" ");

        }
        else if(expression.length()==3)
        {returnValue=expression.split("");
        }
        return returnValue;
    }


    public static String getExpressionFromStdin(){
        Scanner scanner = new Scanner(System.in);

        String expression = scanner.nextLine();
        return  expression;
    }


    public static void assertExpression(String[] parts ) throws Exception{

        if (parts.length != 3) {
            throw new Exception("wrong input");
        }
    }
    public static Expression getAsExpression(String[] parts) {
        return new Expression(
                Integer.parseInt(parts[0]),
                parts[1],
                Integer.parseInt(parts[2]));
    }

    public static String executeExpression(Expression expression){
        Executor executor = new Executor();

        String result = errorResponse;

        switch (Mathop.getMathopName(expression.getOperator()))
        {
            case PLUS:
            {
                result = String.valueOf(expression.getOperand1() + expression.getOperand2());
                break;
            }
            case DIV:
            {
                if(expression.getOperand1()==0 || expression.getOperand2()==0)
                {
                    return result="-";

                }
                else
                {
                    result = String.valueOf(executor.div(expression.getOperand1(), expression.getOperand2()));
                    break;
                }


            }
            case MINUS:
            {
                result = String.valueOf(expression.getOperand1() - expression.getOperand2());
                break;
            }
            case MULTIP:
            {
                result = String.valueOf(executor.multip(expression.getOperand1(), expression.getOperand2()));
                break;
            }
        }


        return result;
    }

    public static void main( String[] args )
    {
        //Analyz analyz = new Analyz();
        //Input input = new Input();
        String result = getErrorResponse();
        try {
            String exprFromStdin = getExpressionFromStdin();
            String[] parts = parse(exprFromStdin);
            assertExpression(parts);
            Expression expression = getAsExpression(parts);
            result = executeExpression(expression);
        }catch(Exception ex ){}
        System.out.print(result);


    }

}
