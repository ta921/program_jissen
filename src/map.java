class Map {
    int[][] map;
    
    boolean wallColissionCheck(MovingObject m){
        int i;
        switch (m.vec) {
        case 1:
            if (m.y-m.h <= 0) {
                return true;
            }
            for (i=0; i<40; i++){
                if (map[(m.y-m.h)/40-1][(m.x-m.w)/40+i] == 1) return true;
            }
            break;
        case 2:
            if (m.x+m.w >= 1260){
                return true;
            }
            for (i=0; i<40; i++){
                if(map[(m.y-m.h)/40+i][(m.x+m.w)/40+1] == 1) return true;
            }
            break;
        case 3:
            if (m.y+m.h >= 960) {
                return true;
            }
            for (i=0; i<40; i++){
                if (map[(m.y+m.h)/40+1][(m.x-m.w)/40+i] == 1) return true;
            }
            break;
        case 4:
            if (m.x-m.w <= 0){
                return true;
            }
            for (i=0; i<40; i++){
                if(map[(m.y-m.h)/40+i][(m.x-m.w)/40-1] == 1) return true;
            }
            break;
        }
        return false;
    }
}
