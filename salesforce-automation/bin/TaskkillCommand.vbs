Dim oShell : Set oShell = CreateObject("WScript.Shell")

' Kill notepad '
oShell.Run "taskkill /f /im chromedriver.exe", , True
oShell.Run "taskkill /f /im chrome.exe", , True