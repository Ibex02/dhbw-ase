Schritte um die Anwendung zu starten
1. docker-compose.yaml ausführen, um eine lokale Datenbank (MongoDB) hochzufahren
2. Die Datenbank startet dann Host: localhost mit dem Port 27017 (localhost:27017), es ist keine Authentifikation notwendig
3. Anschließend kann Swagger gestartet werden, dazu MainApplication ausführen, http://localhost:8080/swagger-ui/index.html
4. Die Anwendung verfügt über eine Authentifizierung, das heißt ohne Anmeldung auf einen spezifischen Nutzer, kann kein Endpunkt benutzt werden, außer den create-Endpunkt des User-Controller
5. Um einen User zu Erstellen muss dieser Endpunkt ausgeführt werden, der User benötigt einen Namen, eine gültige Email (z.B. bla@bla.de) und ein Passwort
6. Ist der User erstellt dann kann man sich bei Swagger über authorize mit der Email und dem Passwort anmelden
7. Anschließend lässt sich die Anwendung komplett testen, bei falschen Nutzerdaten, muss man sich erneut anmelden bei jedem Request und der Request wird nicht ausgeführt