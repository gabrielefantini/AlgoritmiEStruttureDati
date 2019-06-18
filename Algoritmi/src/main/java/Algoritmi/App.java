package Algoritmi;

public final class App {

    private final static int insertCost = 6;
    private final static int deleteCost = 2;
    private final static int copyCost = 1;
    private final static int replaceCost = 2;
    private final static int twiddleCost = 4;
    private final static int killCost = 1;

    private double[][] cost;
    private String[][] operations;

    private App(int x, int y) {
        this.cost = new double[x+1][y+1];
        this.operations = new String[x+1][y+1];
    }

    public String[][] getOperations(){
        return this.operations;
    }


    public static App editDistance(String x, String y){
        App solution = new App(x.length(), y.length());
        for(int i=0; i<=x.length(); i++){
            solution.cost[i][0] = i * App.deleteCost;
            solution.operations[i][0] = "DELETE";
        }
        for(int j=0; j<=y.length(); j++){
            solution.cost[0][j] = j * App.insertCost;
            if(j>0)
                solution.operations[0][j] = "INSERT " + y.charAt(j-1);
            
        }
        for(int i=1; i<=x.length(); i++){
            for(int j=1; j<=y.length(); j++){
                solution.cost[i][j] = Double.POSITIVE_INFINITY;
                if(x.charAt(i-1)==y.charAt(j-1)){
                    solution.cost[i][j] = solution.cost[i-1][j-1] + App.copyCost;
                    solution.operations[i][j] = "COPY";
                }
                if(x.charAt(i-1) != y.charAt(j-1) && solution.cost[i-1][j-1]+App.replaceCost < solution.cost[i][j]){
                    solution.cost[i][j] = solution.cost[i-1][j-1] + App.replaceCost;
                    solution.operations[i][j] = "REPLACE WITH " + y.charAt(j-1);
                }
                if(i>= 2 && j>= 2){
                    if(x.charAt(i-1)==y.charAt(j-2) && x.charAt(i-2)==y.charAt(j-1) && solution.cost[i-2][j-2]+App.twiddleCost<solution.cost[i][j]){
                        solution.cost[i][j] = solution.cost[i-2][j-2] + App.twiddleCost;
                        solution.operations[i][j] = "TWIDDLE";
                    }
                }
                if(solution.cost[i-1][j] + App.deleteCost <solution.cost[i][j]){
                    solution.cost[i][j] = solution.cost[i-1][j] + App.deleteCost;
                    solution.operations[i][j] = "DELETE";
                }
                if(solution.cost[i][j-1] + App.insertCost < solution.cost[i][j]){
                    solution.cost[i][j] = solution.cost[i][j-1] + App.insertCost;
                    solution.operations[i][j] = "INSERT "+ y.charAt(j-1);
                }
            }
        }
        for(int i=0; i<=x.length()-1; i++){
            if(solution.cost[i][y.length()] + App.killCost < solution.cost[x.length()][y.length()]){
                solution.cost[x.length()][y.length()] = solution.cost[i][y.length()];
                solution.operations[x.length()][y.length()] = "KILL " + i;
            }
        }
        return solution;
    }

    public static void operationsPrint(String[][] op, int i, int j){
        int i1=0, j1=0;
        if(i == 0 && j == 0)
            return;
        if(op[i][j].length()>= 8){
            if(op[i][j].substring(0, 7).equals("REPLACE")){
                i1 = i-1;
                j1 = j-1;
            }
            if(op[i][j].substring(0, 6).equals("INSERT")){
                i1 = i;
                j1 = j-1;
            }
        }
        else if(op[i][j].equals("COPY")){
            i1 = i-1;
            j1 = j-1;
        }
        else if(op[i][j].equals("TWIDDLE")){
            i1 = i-2;
            j1 = j-2;
        }
        else if(op[i][j].equals("DELETE")){
            i1 = i-1;
            j1 = j;
        }
        else{
            i1 = Integer.parseInt(op[i][j].substring(5,6));
            j1 = j;
        }
        App.operationsPrint(op, i1, j1);
        System.out.println(op[i][j]);
    }

}