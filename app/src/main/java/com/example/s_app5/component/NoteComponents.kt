package com.example.s_app5.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NotInputText(
    modifier: Modifier=Modifier,
    text:String,
    label:String,
    maxline:Int=1,
    onTextChange:(String) -> Unit,
    onImeAction:() -> Unit={}
){
    val keyboardController=LocalSoftwareKeyboardController.current
    TextField(value = text, onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(),
maxLines = maxline,
        label={ Text(text = label)},
        modifier = Modifier,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions (onDone = {
            onImeAction()
            keyboardController?.hide()
        })

        )

}
@Composable
fun NoteButton(
    modifier: Modifier=Modifier,
    text:String,
    enabeled:Boolean=true,
    onClick:() -> Unit
){
    Button(onClick =onClick,
        shape = CircleShape,
        modifier = Modifier,
        enabled = enabeled
    ) {
        Text(text)

    }

}
