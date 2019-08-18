package edu.mit.eecs.eclipse031;

import static org.eclipse.ui.PlatformUI.getWorkbench;

import java.net.URL;

import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.splash.EclipseSplashHandler;

@SuppressWarnings("restriction")
public class SplashHandler extends EclipseSplashHandler {
    
    private static final String PLUGIN = "edu.mit.eecs.eclipse031.plugin";
    private static final String PREFS_SET = "preferencesSet";
    
    private static final String SETUP_COMMAND = "org.eclipse.oomph.setup.editor.perform";
    
    private final Image image;
    
    public SplashHandler() {
        final URL url = FileLocator.find(Platform.getBundle(PLUGIN), new Path("images/6031.png"));
        this.image = ImageDescriptor.createFromURL(url).createImage();
    }
    
    @Override public void init(Shell splash) {
        super.init(splash);
        
        Label overlay = new Label(this.getContent(), SWT.NONE);
        overlay.setImage(image);
        Rectangle bounds = image.getBounds();
        // overlaying org.eclipse.platform_4.12.0.v20190605-1800/splash.bmp
        overlay.setBounds(178, 90, bounds.width, bounds.height);
        
        getWorkbench().addWindowListener(new IWindowListener() {
            public void windowOpened(IWorkbenchWindow window) { }
            public void windowActivated(IWorkbenchWindow window) {
                getWorkbench().removeWindowListener(this);
                
                if ( ! InstanceScope.INSTANCE.getNode(PLUGIN).getBoolean(PREFS_SET, false)) {
                    getWorkbench().getDisplay().asyncExec(() -> {
                        try {
                            getWorkbench().getService(IHandlerService.class).executeCommand(SETUP_COMMAND, null);
                        } catch (CommandException ce) {
                            ce.printStackTrace();
                        }
                    });
                }
            }
            public void windowDeactivated(IWorkbenchWindow window) { }
            public void windowClosed(IWorkbenchWindow window) { }
        });
    }
    
    @Override public void dispose() {
        super.dispose();
        image.dispose();
    }
}
