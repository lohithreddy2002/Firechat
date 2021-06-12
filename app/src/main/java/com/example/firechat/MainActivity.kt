package com.example.firechat

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firechat.ui.theme.FireChatTheme
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel = viewmodel(repo = repo(database(context = this)))
        setContent {
            FireChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MessageScreen(viewmodel)
                }
            }
        }
    }
}
@Composable
fun UserImage(size:Dp){

        Image(
            painterResource(id = R.drawable.p),
            contentDescription = "image",
            Modifier
                .clip(CircleShape)
                .size(size)
        )


    }

@Composable
fun Bubble(username:String,message:String){
    var allign = Alignment.Start
    if(username == "Lohith" ){
        allign = Alignment.End
    }
    Surface(shape = RoundedCornerShape(20.dp),modifier = Modifier
        .padding(start = 10.dp, top = 10.dp, end = 20.dp, bottom = 10.dp)
        .fillMaxWidth()) {
        Spacer(modifier = Modifier.width(10.dp))
            Column(verticalArrangement = Arrangement.SpaceAround,horizontalAlignment = allign) {

Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .padding(2.dp)
            .background(color = Color.Red)) {

                Text(text = message, modifier = Modifier.padding(10.dp))
            }


        }
    }
}


@Composable
fun MessagePane(list: List<Chat>,viewmodel: viewmodel = viewModel()) {
val listState  = rememberLazyListState()
    val co = rememberCoroutineScope()
    var enable = false
Surface(
    Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
    Column(verticalArrangement = Arrangement.SpaceEvenly) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(), state = listState
        ) {
            items(list) { data ->
                Bubble(data.user, data.messages)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(value = viewmodel.message.value, onValueChange = {viewmodel.onvaluechange(it)},
                label = { Text(text = "enter the message") }, modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
            )
            if(viewmodel.message.value.trim() != ""){
                enable = true
            }
            IconButton(onClick = {viewmodel.sendmessage() },enabled =viewmodel.onemptymessage()) {
                Icon(imageVector = Icons.Filled.Send, contentDescription = "send",tint = Color.Red,modifier = Modifier.size(50.dp))
            }
        }
    }
}

}


@Composable
fun MessageScreen(
    viewmode:viewmodel = viewModel()

){
    val items = viewmode.chat.observeAsState(initial = listOf()).value
    Scaffold(
        topBar = {TopAppBar(
            content = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")

                }
                UserImage(50.dp)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Lohith Reddy")
            }
        )
        },
        content = {
            if(items.isEmpty()){
                Surface(Modifier.fillMaxSize()) {
                    Column(verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()

                    }
                }
            }
            else{
            MessagePane(list = items, viewmode)
        }
        }
    )
}

@Preview
@Composable
fun DefaultPreview() {
    FireChatTheme {
        MessageScreen()
    }
}