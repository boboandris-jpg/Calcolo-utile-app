# Calcolo Utile - App Android per Calcolo Ricavi

Un'app Android nativa in Kotlin con Material Design 3, progettata per calcolare ricavi e utili netti in due regimi fiscali diversi.

## 🎯 Funzionalità Principali

### Regime Ordinario
- Calcolo IVA a debito e detraibile
- Ricavo netto e margine lordo
- Imposte stimate e contributi
- Utile netto e margine percentuale

### Regime Forfettario
- Reddito imponibile
- Imposta sostitutiva (5% o 15%)
- Contributi INPS
- Utile netto finale

### Calcolo Bersaglio (Inverso)
- Calcola il prezzo di vendita necessario per un utile netto desiderato
- Supporta entrambi i regimi

### Impostazioni Persistenti
- Salvataggio automatico di tutti i parametri
- Utilizzo di DataStore
- Valori predefiniti modificabili

## 📊 Calcoli in Tempo Reale
L'app aggiorna i risultati mentre digiti, senza necessità di premere pulsanti.

## 🎨 Design
- Material Design 3
- Interfaccia moderna e intuitiva
- Ottimizzato per smartphone

## 📱 Compatibilità
- Android 10 (API 30) e superiori
- Supporto per schermi di varie dimensioni

## 🛠️ Stack Tecnologico
- **Kotlin** per il linguaggio
- **Jetpack Compose** per l'UI
- **ViewModel + StateFlow** per la gestione dello stato
- **DataStore** per la persistenza dei dati
- **Material Design 3** per i componenti UI

## 📥 Installazione

1. Clona il repository
2. Apri il progetto in Android Studio
3. Sincronizza Gradle
4. Compila e installa l'app

## 📦 Generare l'APK

### Debug APK
```bash
./gradlew assembleDebug
```
L'APK si troverà in: `app/build/outputs/apk/debug/app-debug.apk`

### Release APK
```bash
./gradlew assembleRelease
```
L'APK si troverà in: `app/build/outputs/apk/release/app-release.apk`

## 📄 Licenza
Questo progetto è open source e disponibile per uso libero.

## 👨‍💻 Autore
Creato da boboandris-jpg

---

**Nota**: Per distribuire la Release APK su Google Play Store, è necessario firmarla con una keystore. Per test locali, usa la Debug APK.
