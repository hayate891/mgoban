/*
 *  mGoban - GUI for Go
 *  Copyright (C) 2007, 2009, 2010  sanpo
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package app.sgf.action;

import go.GoGame;
import go.gui.SaveImagePanel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

public class ExportImageAction extends AbstractAction {
    private static final String OK = java.util.ResourceBundle.getBundle("app/resource/Resource").getString("OK");
    private static final String CANCEL = java.util.ResourceBundle.getBundle("app/resource/Resource").getString("Cancel");
    
    private static final String EXPORT_IMAGE = java.util.ResourceBundle.getBundle("app/resource/Resource").getString("ExportImage");
    private GoGame goGame;

    public ExportImageAction(GoGame goGame) {
        putValue(Action.NAME, EXPORT_IMAGE);
        putValue(Action.SHORT_DESCRIPTION, EXPORT_IMAGE);

        this.goGame = goGame;
    }

    public void actionPerformed(ActionEvent e) {
        SaveImagePanel panel = new SaveImagePanel(goGame);
        
        String[] options = {OK, CANCEL};
        int retval = JOptionPane.showOptionDialog(goGame.getWindow(), panel, java.util.ResourceBundle.getBundle("app/resource/Resource").getString("ExportImage"),
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if(retval == 0){
            panel.save();
        }
    }
}