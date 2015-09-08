# Wget-server

## Description
Named after popular shell tool, it is not actually the same thing. Wget-server is a simple proxy, which aims to download big files from remote servers to local network, and then provide them for internal components. Lets say, there are several components, which require the same big file, requests are rather frequent, sometimes simultaneous. Instead of sending several http get request to remote host, components send requests to proxy, which downloads file only once and caches it. Assuming that remote network is slow and subject to internal errors, wget-server will do retries as configured, and guarantee that either all its clients get the result or no one gets it. In common case, request from first http component is slower, while next requests are much faster, as file is distributed among local network (assuming wget-server is in this network). There are some sketches [here](https://github.com/alex-rnv/wget-server/wiki/Diagrams). 

## Usage


## TDB

