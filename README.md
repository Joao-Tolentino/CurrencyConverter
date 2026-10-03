# CurrencyConverter

[![License: AGPL v3](https://img.shields.io/badge/License-AGPL_v3-blue.svg)](LICENSE)
[![Platform: Windows](https://img.shields.io/badge/Platform-Windows-0078D6.svg?logo=windows&logoColor=white)](#)

A Java console application that performs real-time currency conversions. It connects to the ExchangeRate-API via secure HTTP requests to fetch live conversion rates and parses them using Google's GSON library.

---

## Features

- **Live Exchange Rates**: Fetches live rates using `HttpURLConnection` requests to `v6.exchangerate-api.com`.
- **Extensive Support**: Supports dozens of global fiat currencies (utilizing `java.util.Currency` for display names) as well as recognizing cryptocurrency codes like BTC, ETH, ADA, and Solana.
- **Interactive Console Menu**: Easy-to-use numbered menu to browse supported 3-character codes, enter amounts, and output conversion results instantly.

---

## Quick Start

1. Clone or download the repository.
2. Ensure you have the Java SDK (JDK) and Maven installed.
3. Set your ExchangeRate-API key as an environment variable:
   - Windows: `set EXCHANGERATE-API-KEY=your_key_here`
   - Linux/Mac: `export EXCHANGERATE-API-KEY=your_key_here`
4. Build using Maven: `mvn clean install`
5. Run the packaged `.jar` or use `mvn exec:java`.

---

## Configuration Details

You **must** provide a valid `EXCHANGERATE-API-KEY` environment variable. The app retrieves it securely via `System.getenv("EXCHANGERATE-API-KEY")` to construct the API URL. Without this key, the HTTP request will fail, and conversion rates will default to `0.0`.

---

## Usage Guidelines

- Run the application.
- Select `1` to list all supported currency codes (e.g., USD, EUR, BRL) and their full names.
- Select `2` to initiate a conversion. You will be prompted to enter a numeric amount, your input 3-letter currency code, and your target 3-letter output code.
- Select `3` to cleanly exit the application loop.

---

## Technical Documentation

For developers interested in directory structures, code architecture, or compilation guidelines, please refer to the **[Documentation.md](Documentation.md)** file.

---

## License

This project is licensed under the **GNU Affero General Public License Version 3 (AGPLv3)**. See the LICENSE file for details.
