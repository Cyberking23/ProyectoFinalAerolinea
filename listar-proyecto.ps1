# listar-simple.ps1
# Lista carpetas y archivos (rutas relativas) excluyendo basura de IDE/build.
# Salida: estructura.txt

$root = (Resolve-Path .).Path

# Lo que NO quieres incluir (carpetas y extensiones)
$excludeDirs  = @(".git",".idea",".gradle",".mvn","target","build","out","node_modules","dist","logs",".vscode")
$excludeFiles = @("*.iml","*.class","*.log","*.tmp","*.jar","*.war","Thumbs.db",".DS_Store")

$all = Get-ChildItem -Recurse -Force

$filtered = $all | Where-Object {
    $p = $_.FullName

    # Excluir si la ruta contiene alguna carpeta de excludeDirs
    foreach ($d in $excludeDirs) {
        if ($p -match "[\\/]" + [regex]::Escape($d) + "[\\/]") { return $false }
    }

    # Si es archivo, filtrar por patrón
    if (-not $_.PSIsContainer) {
        foreach ($f in $excludeFiles) {
            if ($_.Name -like $f) { return $false }
        }
    }

    return $true
}

$lines = $filtered | ForEach-Object {
    $rel = $_.FullName.Substring($root.Length).TrimStart('\','/')
    if ($rel -eq "") { "." } elseif ($_.PSIsContainer) { "$rel/" } else { $rel }
}

$lines | Sort-Object | Set-Content -Encoding utf8 "estructura.txt"
Write-Host "OK -> estructura.txt"
