package som.openapi.test.generator.popup.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import som.openapi.test.generator.transformation.ATLRunner;
import som.openapi.test.generator.transformation.TestSuiteGenerator;

public class GenerateTestModelAction implements IObjectActionDelegate {

	private Shell shell;

	private IFile iFile;

	/**
	 * Constructor for Action1.
	 */
	public GenerateTestModelAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		// Initialize the model
		IContainer target = iFile.getProject().getFolder("src-gen");
		if (!target.getLocation().toFile().exists()) {
			target.getLocation().toFile().mkdirs();
		}
	
//			
//			try {
//				ATLRunner.run(URI.createURI(iFile.getLocationURI().toString()), URI.createPlatformResourceURI(target.getFullPath().toString(), true)
//						.appendSegment(iFile.getName().substring(0, iFile.getName().lastIndexOf('.')))
//						.appendFileExtension("out.xmi")) ;
//				
//				
//		
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
				try {
			TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();
	
				testSuiteGenerator.generateAndSaveTestSuite( URI.createPlatformResourceURI(iFile.getFullPath().toString(), true),URI.createPlatformResourceURI(target.getFullPath().toString(), true)
						.appendSegment(iFile.getName().substring(0, iFile.getName().lastIndexOf('.')))
						.appendFileExtension("test"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof IFile) {
				iFile = (IFile) structuredSelection.getFirstElement();
			}
		}
	}

}
