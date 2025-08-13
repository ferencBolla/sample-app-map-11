package com.bolla.sampleappmap11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bolla.sampleappmap11.ui.theme.Sampleappmap11Theme
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.style.expressions.dsl.generated.get
import com.mapbox.maps.extension.style.expressions.dsl.generated.literal
import com.mapbox.maps.extension.style.expressions.generated.Expression.Companion.any
import com.mapbox.maps.extension.style.expressions.generated.Expression.Companion.eq
import com.mapbox.maps.extension.style.expressions.generated.Expression.Companion.rgb
import com.mapbox.maps.extension.style.expressions.generated.Expression.Companion.rgba
import com.mapbox.maps.extension.style.expressions.generated.Expression.Companion.toNumber
import com.mapbox.maps.extension.style.layers.generated.fillLayer
import com.mapbox.maps.extension.style.sources.generated.vectorSource
import com.mapbox.maps.extension.style.style

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sampleappmap11Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            MapboxMap(
                Modifier.fillMaxSize(),
                mapViewportState = rememberMapViewportState {
                    setCameraOptions {
                        zoom(19.0)
                        center(Point.fromLngLat(19.091175620535125, 47.48419256932472))
                        pitch(0.0)
                        bearing(0.0)
                    }
                },
            ) {
                MapEffect { mapView ->
                    mapView.mapboxMap.loadStyle(
                        style(com.mapbox.maps.Style.LIGHT) {
                            +vectorSource("source-id") {
                                tiles(listOf("asset://tiles/{z}/{x}/{y}.mvt"))
                                minzoom(10)
                                maxzoom(16)
                            }

                            +fillLayer(
                                layerId = "layer-id",
                                sourceId = "source-id"
                            ) {
                                sourceLayer("geo_FOTAV_META_HOTERX__FewInOne")

                                filter(
                                    any(
                                        eq(get("GT"), literal(0))
                                    )
                                )

                                val r = toNumber(get("R"))
                                val g = toNumber(get("G"))
                                val b = toNumber(get("B"))
                                fillColor(rgba(r, g, b, literal(1.0)))
                                fillOutlineColor(rgb(r, g, b))
                            }
                        }
                    )
                }
            }
        }
    }
}