/*
 * Copyright 2017 Bridje Framework.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bridje.orm.srcgen.edit;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.bridje.orm.srcgen.model.EntityInf;

public class EntityInfEditor extends EditorBase<EntityInf>
{
    private TextField tfName;

    private TextField tfTable;

    private TextArea taDescription;

    public EntityInfEditor()
    {
        super();
        tfName = addRow("Name", createTextField());
        tfTable = addRow("Table", createTextField());
        taDescription = addRow("Description", createTextArea(3));
        modelProperty().addListener((observable, oldValue, newValue) ->
        {
            tfName.setText(newValue.getName());
            tfTable.setText(newValue.getTable());
            taDescription.setText(newValue.getDescription());
        });
    }

    public void updateModel()
    {
        getModel().setName(tfName.getText());
        getModel().setTable(tfTable.getText());
        getModel().setDescription(taDescription.getText());
    }
}
