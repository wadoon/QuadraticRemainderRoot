package weigla.qw.client;

import weigla.qw.math.NoSolutionException;
import weigla.qw.math.RemainderRoot;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class QW_Web implements EntryPoint, ClickHandler {

    TextBox txtA = null;
    TextBox txtN = null;
    Button btnSubmit = null;
    private HTMLPanel lastSolutionPanel;

    public void onModuleLoad() {
	txtA = new TextBox();
	txtN = new TextBox();
	btnSubmit = new Button("!");

	RootPanel p = RootPanel.get("formPanel");
	HorizontalPanel form = new HorizontalPanel();
	form.getElement().setAttribute("align", "center");
	form.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	form.add(new HTMLPanel("<em>x</em><sup>2</sup> ≡ "));
	form.add(txtA);
	form.add(new HTMLPanel(" mod "));
	form.add(txtN);
	form.add(btnSubmit);
	p.add(form);
	btnSubmit.addClickHandler(this);
    }

    @Override
    public void onClick(ClickEvent event) {
	try {
	    int n = Integer.parseInt(txtN.getText());
	    int a = Integer.parseInt(txtA.getText());
	    int[] sol = new int[4];
	    int c = RemainderRoot.root(a, n, sol);

	    RootPanel solution = RootPanel.get("result");

	    String html = "";
	    for (int i = 0; i < c; i++) {
		html += "<em>x</em><sub>" + i + "</sub> = " + sol[i] + "<br>";
	    }

	    if (lastSolutionPanel != null) {
		lastSolutionPanel.removeFromParent();
	    }

	    lastSolutionPanel = new HTMLPanel(html);
	    solution.add(lastSolutionPanel);
	} catch (NoSolutionException e) {
	    // Create the popup dialog box
	    final DialogBox dialogBox = new DialogBox();
	    dialogBox.setText("Fehler - keine Lösung");
	    dialogBox.setAnimationEnabled(true);
	    dialogBox.setGlassEnabled(true);
	    final Button closeButton = new Button("Close");
	    VerticalPanel dialogVPanel = new VerticalPanel();
	    dialogVPanel.add(new HTML(e.getMessage()));
	    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
	    dialogVPanel.add(closeButton);
	    dialogBox.setWidget(dialogVPanel);

	    // Add a handler to close the DialogBox
	    closeButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
		    dialogBox.hide();
		    txtA.setFocus(true);
		}
	    });
	    dialogBox.center();
	    closeButton.setFocus(true);
	}

    }
}
