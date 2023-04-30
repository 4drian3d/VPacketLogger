# VPacketLogger

[![WorkFlow](https://img.shields.io/github/actions/workflow/status/4drian3d/VPacketLogger/build.yml?style=flat-square)](https://github.com/MiniPlaceholders/MiniPlaceholders/actions)
![Latest Version](https://img.shields.io/github/v/release/4drian3d/VPacketLogger?style=flat-square)
[![Discord](https://img.shields.io/discord/899740810956910683?color=7289da&logo=Discord&label=Discord&style=flat-square)](https://discord.gg/5NMMzK5mAn)
![Modrinth Downloads](https://img.shields.io/modrinth/dt/HQyibRsN?logo=Modrinth&style=flat-square)
![GitHub Downloads](https://img.shields.io/github/downloads/4drian3d/VPacketLogger/total?logo=GitHub&style=flat-square)

Simple Velocity plugin to log Packet executions

## Installation
- Install [VPacketEvents](https://modrinth.com/plugin/vpacketevents)
- Download VPacketLogger from [Modrinth](https://modrinth.com/plugin/vpacketlogger)
- Drag and drop on your plugins folder
- Start the server

## How it works

VPacketLogger simply logs every packet sent or received between the proxy and the player.
It can only intercept packets that are handled by Velocity, whether they are native or logged by other plugins.

In the configuration you can set which placeholders to use, MiniMessage format is supported.
