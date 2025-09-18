# SwiftExchange

SwiftExchange is a currency conversion program built on top of the W-hrung project. While W-hrung provided a double-table setup for managing currencies, SwiftExchange enhances it by integrating live exchange rates through the Forex-Exchange API, [Exchangerate.host](https://exchangerate.host/) making conversions more accurate and up to date.

## Features

🔄 Real-time exchange rates from Forex-Exchange.

📝 Built-in logging system to keep track of all requests

## Requirements

An API key from Exchange.host. You have to make an account and get a Free API-Key with 100 free Requests a month

## What I learned
- How to make API requests and integrate them into my code.

- That it’s not always as simple as copying an example and pasting it into your project.

- Sometimes changing even a small part of a working request can break it — I once spent two hours fixing a bug I introduced this way.
## Roadmap / Future Improvements

-🌍 Multi-language support.

📦 JSON parsing with external libraries (instead of manual handling).

-🔑 In-app method to securely save and manage the API key.

- Logger function can be in a SQL parsed

## Available Currencies

The full list of supported currencies can be found [here](https://exchangerate.host/currencies)
