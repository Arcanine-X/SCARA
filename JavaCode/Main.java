
/* Code for Assignment ?? 
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.*;

/** <description of class Main>
 */
public class Main{

    private Arm arm;
    private Drawing drawing;
    private ToolPath tool_path;
    // state of the GUI
    private int state; // 0 - nothing
    // 1 - inverse point kinematics - point
    // 2 - enter path. Each click adds point  
    // 3 - enter path pause. Click does not add the point to the path

    /**      */
    public Main(){
        UI.initialise();
        UI.addButton("xy to angles", this::inverse);
        UI.addButton("Enter path XY", this::enter_path_xy);
        UI.addButton("Save path XY", this::save_xy);
        UI.addButton("Load path XY", this::load_xy);
        UI.addButton("Save path Ang", this::save_ang);
        UI.addButton("Load path", this::load_ang);
        UI.addButton("save the pwm", this::savepwmfile);
        UI.addButton("square",this::drawSquare);
        UI.addButton("doCircle", this::doCircle);
        UI.addButton("drawSkynet", this::drawSkynet);
        UI.addButton("S", this::drawSNet);
        UI.addButton("K", this::drawKNet);
        UI.addButton("Y", this::drawYNet);
        UI.addButton("N", this::drawNNet);
        UI.addButton("E", this::drawENet);
        UI.addButton("T", this::drawTNet);
        // UI.addButton("Quit", UI::quit);
        UI.setMouseMotionListener(this::doMouse);
        UI.setKeyListener(this::doKeys);

        //ServerSocket serverSocket = new ServerSocket(22); 
        this.arm = new Arm();
        this.drawing = new Drawing();
        this.run();
        this.tool_path= new ToolPath();
        arm.draw();
    }

    public void doCircle(){

        double centreX = 340;
        double centreY =140;
        double a = 38.0;
        double b = 35.0;
        //double r = 37.5;
        for(double x = centreX - a; x < centreX + a; x+=5){
            double y = Math.sqrt(b*b*(1-(Math.pow(x - centreX,2)/a/a))) + centreY;
            doMouse("clicked",x,y);

        }
        for(double x = centreX - a; x < centreX + a; x+=5){
            double y = -Math.sqrt(b*b*(1-(Math.pow(x - centreX,2)/a/a))) + centreY;
            doMouse("clicked",x,y);

        }
        for(double x = centreX - a; x < centreX + a; x+=5){
            double y = Math.sqrt(b*b*(1-(Math.pow(x - centreX,2)/a/a))) + centreY;
            doMouse("clicked",x,y);

        }

    }

    public void drawSquare(){
        doMouse("clicked",283,138);//top left
        doMouse("clicked",313,138);//top right
        doMouse("clicked",313,178);//bottom right
        doMouse("clicked",283,178);//bottom left
        doMouse("clicked",283,138);//top left
    }

    public void drawSkynet(){
        //S
        doMouse("clicked",184,154);
        doMouse("clicked",164,154);
        doMouse("clicked",164,174);
        doMouse("clicked",184,174);
        doMouse("clicked",184,194);
        doMouse("clicked",164,194);
        //k
        doMouse("clicked",204,154);
        doMouse("clicked",204,194);
        doMouse("clicked",204,174);
        doMouse("clicked",224,194);
        doMouse("clicked",204,174);
        doMouse("clicked",224,154);
        //y
        doMouse("clicked",264,154);
        doMouse("clicked",244,194);
        doMouse("clicked",254,174);
        doMouse("clicked",244,154);
        //N
        doMouse("clicked",280,194);
        doMouse("clicked",280,154);
        doMouse("clicked",305, 194);
        doMouse("clicked", 305, 154);
        //E
        doMouse("clicked", 320, 154);
        doMouse("clicked", 320, 194);
        doMouse("clicked", 335, 194);
        doMouse("clicked", 320, 170);
        doMouse("clicked", 335, 170);
        doMouse("clicked", 320, 154);
        doMouse("clicked", 335, 154);
        //T
        doMouse("clicked", 350,154);
        doMouse("clicked", 380,154);
        doMouse("clicked", 365, 154);
        doMouse("clicked", 365, 194);
    }

    public void drawSNet(){
        drawS();
    }

    public void drawKNet(){
        drawK();
    }

    public void drawYNet(){
        drawY();
    }

    public void drawNNet(){
        drawN();
    }

    public void drawENet(){
        drawE();
    }

    public void drawTNet(){
        drawT();
    }

    public void drawS(){
        doMouse("clicked",184,154);
        doMouse("clicked",164,154);
        doMouse("clicked",164,174);
        doMouse("clicked",184,174);
        doMouse("clicked",184,194);
        doMouse("clicked",164,194);
    }

    public void drawK(){
        doMouse("clicked",204,154);
        doMouse("clicked",204,194);
        doMouse("clicked",204,174);
        doMouse("clicked",224,194);
        doMouse("clicked",204,174);
        doMouse("clicked",224,154);
    }

    public void drawY(){
        doMouse("clicked",264,154);
        doMouse("clicked",244,194);
        doMouse("clicked",254,174);
        doMouse("clicked",244,154);
    }

    public void drawN(){
        doMouse("clicked",280,194);
        doMouse("clicked",280,154);
        doMouse("clicked",305, 194);
        doMouse("clicked", 305, 154);
    }

    public void drawE(){

        doMouse("clicked",335,154);
        doMouse("clicked", 320,154 );
        doMouse("clicked", 320,194);
        doMouse("clicked",334,194 );
        doMouse("clicked", 320,194 );
        doMouse("clicked", 320,170);
        doMouse("clicked", 335,170 );

        //         doMouse("clicked", 320, 154);
        //         doMouse("clicked", 320, 194);
        //         doMouse("clicked", 335, 194);
        //         doMouse("clicked", 320, 170);
        //         doMouse("clicked", 335, 170);
        //         doMouse("clicked", 320, 154);
        //         doMouse("clicked", 335, 154);
    }

    public void drawT(){
        doMouse("clicked", 350,154);
        doMouse("clicked", 380,154);
        doMouse("clicked", 365, 154);
        doMouse("clicked", 365, 194);
    }

    public void savepwmfile(){
        tool_path=new ToolPath();
        tool_path.convert_drawing_to_angles(drawing,arm,"");
        tool_path.convert_angles_to_pwm(arm);
        tool_path.save_pwm_file();
    }

    public void doKeys(String action){
        UI.printf("Key :%s \n", action);
        if (action.equals("b")) {
            // break - stop entering the lines
            state = 3;
            //

        }

    }

    public void doMouse(String action, double x, double y) {
        //UI.printf("Mouse Click:%s, state:%d  x:%3.1f  y:%3.1f\n",
        //   action,state,x,y);
        UI.clearGraphics();
        String out_str=String.format("%3.1f %3.1f",x,y);
        UI.drawString(out_str, x+10,y+10);
        // 
        if ((state == 1)&&(action.equals("clicked"))){
            // draw as 

            arm.inverseKinematic(x,y);
            arm.draw();
            return;
        }

        if ( ((state == 2)||(state == 3))&&action.equals("moved") ){
            // draw arm and path
            arm.inverseKinematic(x,y);
            arm.draw();

            // draw segment from last entered point to current mouse position
            if ((state == 2)&&(drawing.get_path_size()>0)){
                PointXY lp = new PointXY();
                lp = drawing.get_path_last_point();
                //if (lp.get_pen()){
                UI.setColor(Color.GRAY);
                UI.drawLine(lp.get_x(),lp.get_y(),x,y);
                // }
            }
            drawing.draw();
        }

        // add point
        if (   (state == 2) &&(action.equals("clicked"))){
            // add point(pen down) and draw
            UI.printf("Adding point x=%f y=%f\n",x,y);
            //drawing.add_point_to_path(x,y,true); // add point with pen down

            arm.inverseKinematic(x,y);
            if(arm.getValidState()){
                drawing.add_point_to_path(x,y,true); // add point with pen down
                arm.draw();
                drawing.draw();
                drawing.print_path();
            }
        }

        if ((state == 3) &&(action.equals("clicked"))){
            // add point and draw
            //UI.printf("Adding point x=%f y=%f\n",x,y);
            // drawing.add_point_to_path(x,y,false); // add point wit pen up

            arm.inverseKinematic(x,y);
            if(arm.getValidState()){
                drawing.add_point_to_path(x,y,false); // add point wit pen up     
                arm.draw();
                drawing.draw();
                drawing.print_path();
                state = 2;
            }
        }

    }

    public void save_xy(){
        state = 0;
        String fname = UIFileChooser.save();
        drawing.save_path(fname);
    }

    public void enter_path_xy(){
        state = 2;
    }

    public void inverse(){
        state = 1;
        arm.draw();
    }

    public void load_xy(){
        state = 0;
        String fname = UIFileChooser.open();
        drawing.load_path(fname);
        drawing.draw();

        arm.draw();
    }

    //save angles into the file
    public void save_ang(){
        String fname = UIFileChooser.open();
        tool_path.convert_drawing_to_angles(drawing,arm,fname);
    }

    public void load_ang(){
        
    }

    public void run() {
        while(true) {
            arm.draw();
            UI.sleep(20);
        }
    }

    public static void main(String[] args){
        Main obj = new Main();
    }    

}
