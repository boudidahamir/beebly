/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceUser;

/**
 * Sign in UI
 *
 * @author amirb
 */
public class FgtpwdForm extends BaseForm {

    
    public FgtpwdForm(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        username.setSingleLineTextArea(false);
        Button submit = new Button("submit");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label doneHaveAnAccount = new Label("Remember your password?");
        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                submit,
                FlowLayout.encloseCenter(doneHaveAnAccount, signIn)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        submit.requestFocus();
        submit.addActionListener((e)->{
             if (username.getText().length()==0)
                    Dialog.show("Alert", "Please write your username", new Command("OK"));
                 else {
             ServiceUser.getInstance().fgtpwd(username);
             if (ServiceUser.connectedUser!=null){
                 Dialog.show("Email", "An email have been sent", new Command("OK"));
                 new SignInForm(res).show();
                 }
             }
            
});
        signIn.addActionListener((e)->{
            new SignInForm(res).show();
});
    }
    
}
