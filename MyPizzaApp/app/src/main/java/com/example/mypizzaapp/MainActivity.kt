package com.example.mypizzaapp

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.tappableElementIgnoringVisibility
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mypizzaapp.domain.models.Pizza
import com.example.mypizzaapp.ui.PizzaViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.mypizzaapp.ui.theme.PizzeriaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PizzeriaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val inner = innerPadding
                    PizzeriaApp()
                }
            }
        }
    }
}

@Composable
fun PizzeriaApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "menu"
    ){
        composable("menu"){
            MenuScream(navController)

        }
        composable("detalle/{pizzaName}"){ backstackEntry ->
            val pizzaName = backstackEntry.arguments?.getString("pizzaName")

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScream(navController: NavController, viewModel: PizzaViewModel = viewModel()
){
    val pizzas = viewModel.pizzaList

    Scaffold(
        topBar = { TopAppBar(title = {Text("Menú de Pizza") }) }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(pizzas){pizza ->
                PizzaItem(pizza = pizza){
                    navController.navigate("detalle/${pizza.type}")
                }
            }
        }
    }

}

@Composable
fun PizzaItem(pizza: Pizza, onClick : () -> Unit){
    Card(modifier = Modifier.fillMaxWidth()
        .padding(8.dp)
        .clickable{ onClick()},
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
               painter = painterResource(id = pizza.imageRes),
                contentDescription = pizza.type,
                modifier = Modifier.size(80.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(pizza.type, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "Precio: $${pizza.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaMenuScream(viewModel: PizzaViewModel = viewModel()){
    val pizzas = viewModel.pizzaList

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú de Pizzas", fontSize = 20.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding -> LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(pizzas) { pizza ->
                PizzaCard(pizza)
            }
        }
    }
}

@Composable
fun PizzaCard(pizza: Pizza){
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ){
            Image(
                painter = painterResource(id = pizza.imageRes),
                contentDescription = pizza.type,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = pizza.type,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Precio: $${pizza.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaDetailScreen(pizzaName: String?,
                      viewModel: PizzaViewModel = viewModel()
){
    val pizza = remember(pizzaName){ viewModel.finPizzasByName(pizzaName) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Detalle de ${pizza?.type ?: pizzaName}")})}
    ) {
        padding ->
        Column(
            modifier = Modifier.padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (pizza != null){
                Text("Tipo: ${pizza.type}", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                Text("Precio: $${pizza.price}")
                Spacer(Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = pizza.imageRes),
                    contentDescription = pizza.type,
                    modifier = Modifier.size(160.dp)
                )
            }else {
                Text("No se encontró información para '$pizzaName'")
            }
        }
    }
}
