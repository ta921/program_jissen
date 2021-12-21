import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import java.awt.*;
import javax.swing.*;
import javax.media.j3d.*;
import javax.vecmath.*;
 
public class J3DCG extends JPanel {
  public static void main (String[] args) {
    J3DCGPanel j3dcgpanel = new J3DCGPanel();
    JFrame frame = new JFrame("J3DCG.java");
    frame.getContentPane().add(j3dcgpanel);
    frame.setSize(300,300);
    frame.setVisible(true);
    frame.setBackground(Color.white);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
 
class J3DCGPanel extends JPanel {
  public J3DCGPanel() {
    setLayout(new BorderLayout());
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas = new Canvas3D(config);
    add(canvas, BorderLayout.CENTER);
    SimpleUniverse universe = new SimpleUniverse(canvas);
    BranchGroup root = new BranchGroup();
    Sphere sphere = new Sphere(0.6f);
    root.addChild(sphere);
    Color3f lightColor = new Color3f(1.0f, 0.0f, 1.0f);
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100);
    Vector3f lightDirection = new Vector3f(4.0f, 5.0f, -10.0f);
    DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
    light.setInfluencingBounds(bounds);
    root.addChild(light);
    universe.getViewingPlatform().setNominalViewingTransform();
    universe.addBranchGraph(root);
  }
}