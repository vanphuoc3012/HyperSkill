class Problem {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println(-1);
        } else {
                boolean found = false;
                for(int i = 0; i < args.length; i++) {
                    if(args[i].equals("test")) {
                        System.out.println(i);
                        found = true;
                    }
                }
                if(!found) {
                    System.out.println(-1);
                }
        }
    }
}