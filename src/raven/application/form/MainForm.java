package raven.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import raven.application.Application;
import raven.application.form.other.FormDashboard;
import raven.application.form.other.FormKasir;
import raven.application.form.other.FormTransaksi;
import raven.application.form.other.FormMasterProduk;
import raven.application.form.other.FormProdukMasuk;
import raven.application.form.other.FormReturs;
import raven.application.form.other.FormReturBarang;
import raven.application.form.other.ReturBarang;
import raven.application.form.other.FormUser;
import raven.application.form.other.InputGantiPassword;
import raven.menu.Menu;
import raven.menu.MenuAction;
import raven.model.modelUser;
import raven.model.session;

/**
 *
 * @author Raven
 */


/**
 *
 * @author Raven
 */
public class MainForm extends JLayeredPane {
    private modelUser model;
    public MainForm() {
        init();
        this.model = new modelUser();
     
    }

    private void init() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
        menu = new Menu();
        panelBody = new JPanel(new BorderLayout());
        initMenuArrowIcon();
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");
        menuButton.addActionListener((ActionEvent e) -> {
            setMenuFull(!menu.isMenuFull());
        });
        initMenuEvent();
        setLayer(menuButton, JLayeredPane.POPUP_LAYER);
        add(menuButton);
        add(menu);
        add(panelBody);
    }

    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        super.applyComponentOrientation(o);
        initMenuArrowIcon();
    }

    private void initMenuArrowIcon() {
        if (menuButton == null) {
            menuButton = new JButton();
        }
        String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
        menuButton.setIcon(new FlatSVGIcon("raven/icon/svg/" + icon, 0.8f));
    }
    public void getModelUser(modelUser model) {
     this.model = model;
        menu.setModelUser(model);
        
        
    }

    private void initMenuEvent() {
        session sess = session.getInstance();
        int idUser = sess.getUserId();
        String nama = sess.getUsername();

        
        
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
             String role = model != null ? model.getRole() : null;
//         if (!isAllowedForRole(index, subIndex, role)) {
//            action.cancel();
//            return;
//        }


if (index == 0) {
    // Dashboard accessible to all roles
    Application.showForm(new FormDashboard());
} else if ("kasir".equals(role)) {
    // Kasir access
    if (index == 1) {
        if (subIndex == 1) {
            Application.showForm(new FormKasir());
        } else if (subIndex == 2) {
            Application.showForm(new FormTransaksi(model));
        } else {
            action.cancel();
        }
    } else if (index == 2) {
        if (subIndex == 1) {
            Application.showForm(new FormReturBarang());
        } else if (subIndex == 2) {
            Application.showForm(new FormReturs());
        } else {
            action.cancel();
        }
    } else if (index == 3) {
        Application.logout();
    } else {
        action.cancel();
    }
} else if ("Manajemen Stok".equals(role)) {
    if (index == 1) {
        if (subIndex == 1) {
            Application.showForm(new FormMasterProduk());
        } else if (subIndex == 2) {
            Application.showForm(new FormProdukMasuk());
        } else {
            action.cancel();
        }
        } else if (index == 2) {
            Application.logout();
        } else {
            action.cancel();
        }
}else if ("admin".equals(role)) {
    // Admin has full access
    if (index == 1) {
        if (subIndex == 1) {
            Application.showForm(new FormKasir());
        } else if (subIndex == 2) {
            Application.showForm(new FormTransaksi(model));
        } else {
            action.cancel();
        }   
    } else if (index == 2) {
        if (subIndex == 1) {
            Application.showForm(new FormMasterProduk());
        } else if (subIndex == 2) {
            Application.showForm(new FormProdukMasuk());
        } else {
            action.cancel();
        }   
    } else if (index == 3) {
        if (subIndex == 1) {
            Application.showForm(new FormUser()); 
        } else if (subIndex == 2) {
            new InputGantiPassword(Application.getApp(), true, model).setVisible(true);
        } else {
            action.cancel();
        }  
    } else if (index == 4) {
        if (subIndex == 1) {
            Application.showForm(new FormReturBarang()); 
        } else if (subIndex == 2) {
            Application.showForm(new FormReturs());
        } else {
            action.cancel();
        }
    } else if (index == 5) {
        Application.logout(); 
    } else {
        action.cancel();
    }
} else {
    // Default for unknown roles
    action.cancel();
}
        });
    }

    private void setMenuFull(boolean full) {
        String icon;
        if (getComponentOrientation().isLeftToRight()) {
            icon = (full) ? "menu_left.svg" : "menu_right.svg";
        } else {
            icon = (full) ? "menu_right.svg" : "menu_left.svg";
        }
        menuButton.setIcon(new FlatSVGIcon("raven/icon/svg/" + icon, 0.8f));
        menu.setMenuFull(full);
        revalidate();
    }
    private boolean isAllowedForRole(int index, int subIndex, String role) {
    switch (role) {
        case "admin":
            return true;
        case "kasir":
            return index == 0 || (index == 1 && (subIndex == 1 || subIndex == 2)) || (index == 2 && (subIndex == 1 || subIndex == 2)) || (index == 5 && (subIndex == 1 || subIndex == 2)) || index == 6;
        case "Manajemen Stok":
            return index == 0 || (index == 1 && (subIndex == 1 || subIndex == 2)) || (index == 3 && (subIndex == 1 || subIndex == 2)) || index == 6;
        default:
            return false;
    }
}
    public void hideMenu() {
        menu.hideMenuItem();
    }

    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void setSelectedMenu(int index, int subIndex) {
        menu.setSelectedMenu(index, subIndex);
    }

    private Menu menu;
    private JPanel panelBody;
    private JButton menuButton;

    private class MainFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int menuWidth = UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
                int menuX = ltr ? x : x + width - menuWidth;
                menu.setBounds(menuX, y, menuWidth, height);
                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                int menubX;
                if (ltr) {
                    menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
                } else {
                    menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
                }
                menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
                int gap = UIScale.scale(5);
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = ltr ? (x + menuWidth + gap) : x;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
            }
        }
    }
}
