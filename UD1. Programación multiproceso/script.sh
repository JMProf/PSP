#!/bin/bash

# Saludar al usuario
echo "Hola, $(whoami)!"

# Crear una carpeta llamada 'nueva_carpeta'
mkdir -p nueva_carpeta
echo "Se ha creado la carpeta 'nueva_carpeta'."

# Mostrar los ficheros en el directorio actual
echo "Los archivos y carpetas en el directorio actual son:"
ls
