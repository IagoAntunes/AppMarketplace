package com.iagoaf.appmarketplace.services.database

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json

object AppSupabase {

    val instance: SupabaseClient by lazy {
        createSupabaseClient(
            "https://papwmqzausgnhouifweq.supabase.co",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBhcHdtcXphdXNnbmhvdWlmd2VxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzgyNDc0MzMsImV4cCI6MjA1MzgyMzQzM30.zwFX2AhWcPZOzmGqcyJ1WJYVx6o0KmzUcf5fpNpiQGQ"
        ) {
            install(Auth) {
                // Configuração de autenticação, se necessário
            }
            install(Postgrest) {

            }
            defaultSerializer = KotlinXSerializer(Json)
        }
    }
}