class Map {
    int[][] map;
    
    boolean wallColissionCheck(MovingObject m){
        int i;
        System.out.println(m.vec);
        switch (m.vec) {
        case 1:
            //System.out.println(" [w] pressed");
            if (m.y <= 0) {
                return true;
            }
            for (i=0; i<40; i++){
                if (map[m.y/40-1][(m.x+i)/40] == 1) return true;
            }
            break;

        case 2:
            //System.out.println(" [d] pressed");
            if (m.x+m.w >= 1260){
                return true;
            }
            for (i=0; i<40; i++){
                if(map[(m.y+i)/40][(m.x+m.w)/40+1] == 1) return true;
            }
            break;

        case 3:
            //System.out.println(" [s] pressed");
            if (m.y+m.h >= 960) {
                return true;
            }
            for (i=0; i<40; i++){
                if (map[(m.y+m.h)/40+1][(m.x+i)/40] == 1) return true;
            }
            break;

        case 4:
            //System.out.println(" [a] pressed");
            if (m.x <= 0){
                return true;
            }
            for (i=0; i<40; i++){
                if(map[(m.y+i)/40][(m.x)/40-1] == 1) return true;
            }
            break;
        }
        return false;
    }
}
