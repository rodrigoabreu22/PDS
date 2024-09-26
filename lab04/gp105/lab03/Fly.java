public class Fly {
    private Plane plane;
    private String code;
    private int reservations;
    
    public Fly(Plane plane, String code) {
        this.plane = plane;
        this.code = code;
        this.reservations=0;
    }
    public Plane getPlane() {
        return plane;
    }
    public void setPlane(Plane plane) {
        this.plane = plane;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public int getReservations() {
        return reservations;
    }
    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    public int getEmptySeatsExecutive(){
        int[][] seats = plane.getEplaces();
        int count=0;
        for(int row = 0; row < plane.geteRows(); row++){
            for(int seat = 0; seat < plane.geteSeatsPerRow(); seat++){
                if(seats[row][seat]==0){
                    count++;
                }
            }
        }
        return count;
    }

    public int getEmptyRowExecutive(){
        int[][] seats = plane.getEplaces();
        boolean emptyrow;
        for(int row = 0; row < plane.geteRows(); row++){
            emptyrow=true;
            for(int seat = 0; seat < plane.geteSeatsPerRow(); seat++){
                if(seats[row][seat]!=0){
                    emptyrow=false;
                    break;
                }
            }
            if(emptyrow){
                return row-1;
            }
        }
        return -1;
    }

    public boolean addExecutiveReservation(int n){
        int emptyRow, count=0;
        int[][] seats = plane.getEplaces();
        if(getEmptySeatsExecutive()<n){
            return false;
        }
        this.reservations++;
        emptyRow = getEmptyRowExecutive();
        if(emptyRow==-1){
            outloop:
            for(int row = 0; row < plane.geteRows(); row++){
                for(int seat = 0; seat < plane.geteSeatsPerRow(); seat++){
                    if(seats[row][seat]==0){
                        plane.fillExecutiveSeat(row,seat,this.reservations);
                        count++;
                        if(count==n){
                            break outloop;
                        }
                    }
                }
            }
        }
        else{
            outerloop:
            for(int row = emptyRow-1; row < plane.geteRows(); row++){
                for(int seat = 0; seat < plane.geteSeatsPerRow(); seat++){
                    if(seats[row][seat]==0){
                        plane.fillExecutiveSeat(row,seat,this.reservations);
                        count++;
                    }
                    if(count==n){
                        break outerloop;
                    }
                }
                if(row == plane.geteRows()-1 && count!=n){
                    row=0;
                }
            }
        }
        return true;
    }

    public int getEmptySeatsTuristic(){
        int[][] seats = plane.getTplaces();
        int count=0;
        for(int row = 0; row < plane.gettRows(); row++){
            for(int seat = 0; seat < plane.gettSeatsPerRow(); seat++){
                if(seats[row][seat]==0){
                    count++;
                }
            }
        }
        return count;
    }

    public int getEmptyRowTuristic(){
        int[][] seats = plane.getTplaces();
        boolean emptyrow;
        for(int row = 0; row < plane.gettRows(); row++){
            emptyrow=true;
            for(int seat = 0; seat < plane.gettSeatsPerRow(); seat++){
                if(seats[row][seat]!=0){
                    emptyrow=false;
                    break;
                }
            }
            if(emptyrow){
                return row-1;
            }
        }
        return -1;
    }

    public boolean addTuristicReservation(int n){
        int emptyRow, count=0;
        int[][] seats = plane.getTplaces();
        if(getEmptySeatsTuristic()<n){
            return false;
        }
        emptyRow = getEmptyRowTuristic();
        if(emptyRow==-1){
            outloop:
            for(int row = 0; row < plane.gettRows(); row++){
                for(int seat = 0; seat < plane.gettSeatsPerRow(); seat++){
                    if(seats[row][seat]==0){
                        plane.fillTuristicSeat(row,seat,this.reservations);
                        count++;
                        if(count==n){
                            break outloop;
                        }
                    }
                }
            }
        }
        else{
            outerloop:
            for(int row = emptyRow-1; row < plane.gettRows(); row++){
                for(int seat = 0; seat < plane.gettSeatsPerRow(); seat++){
                    if(seats[row][seat]==0){
                        plane.fillTuristicSeat(row,seat,this.reservations);
                        count++;
                    }
                    if(count==n){
                        break outerloop;
                    }
                }
                if(row == plane.gettRows()-1 && count!=n){
                    row=0;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Fly other = (Fly) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }
}
