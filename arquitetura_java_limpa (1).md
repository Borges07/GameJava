# 📦 Organização de Dados no Projeto (Java)

## 🎯 Problema Atual

Hoje os dados estão sendo criados direto no código:

```java
new ObjectItem("Sword", "Broken sword", 44, "path");
```

Problemas:
- ❌ Código difícil de manter
- ❌ Dados duplicados
- ❌ Difícil adicionar novos itens
- ❌ Mistura de responsabilidade (dados + lógica)

---

## ✅ Solução

Separar:
- **Dados** → arquivos JSON
- **Código** → classes Java

👉 O código não cria dados, ele apenas usa os dados

---

## 🧠 Conceito Simples

Você terá 3 camadas:

1. **Data (JSON)** → onde ficam os dados
2. **Data Class (DTO)** → representa o JSON
3. **Model (ObjectItem)** → objeto usado no jogo

---

## 🗂️ Arquitetura de Pastas

```
src/
│
├── model/
│   ├── ObjectItem.java
│   └── Scenario.java
│
├── data/
│   ├── dto/
│   │   ├── ObjectItemData.java
│   │   └── ScenarioData.java
│   │
│   └── repository/
│       ├── ObjectItemRepository.java
│       └── ScenarioRepository.java
│
├── resources/
│   ├── objectitem/
│   │   ├── sword.json
│   │   ├── shield.json
│   │   └── potion.json
│   │
│   └── scenario/
│       ├── pyramid.json
│       └── cave.json
```

---

## 📄 Como montar um JSON

Cada arquivo representa **um item do jogo**:

```json
{
  "nameObject": "Sword",
  "description": "Broken sword",
  "scenarioId": 44,
  "path": "public/image10.txt"
}
```

👉 Quer criar outro item?  
👉 Cria outro arquivo JSON

---

## 🧱 Classe de Dados (DTO)

```java
public class ObjectItemData {
    public String nameObject;
    public String description;
    public Integer scenarioId;
    public String path;
}
```

👉 Essa classe só representa o JSON

---

## 📦 Repository (leitura dos dados)

Responsável por carregar os JSONs:

```java
public class ObjectItemRepository {

    public List<ObjectItemData> loadAll() {
        // aqui você pode usar Gson ou Jackson depois
        return new ArrayList<>();
    }
}
```

---

## ⚙️ Como transformar DATA → OBJETO (parte que mais confunde)

### 1) Converter um único item

```java
ObjectItemData data = ...; // veio do repository

ObjectItem item = new ObjectItem(
    data.nameObject,
    data.description,
    data.scenarioId,
    data.path
);
```

👉 Regra: **Data vira ObjectItem**

---

### 2) Converter uma lista inteira

```java
List<ObjectItem> items = repository.loadAll().stream()
    .map(data -> new ObjectItem(
        data.nameObject,
        data.description,
        data.scenarioId,
        data.path
    ))
    .toList();
```

---

### 3) Filtrar por cenário (ex: scenarioId = 44)

```java
List<ObjectItem> items = repository.loadAll().stream()
    .filter(data -> data.scenarioId == 44)
    .map(data -> new ObjectItem(
        data.nameObject,
        data.description,
        data.scenarioId,
        data.path
    ))
    .toList();
```

👉 Aqui você "puxa" só os itens daquele cenário

---

### 4) Como "setar" no Scenario

Supondo que `Scenario` tem lista de objetos:

```java
Scenario scenario = ...;

List<ObjectItem> items = repository.loadAll().stream()
    .filter(data -> data.scenarioId == scenario.getScenarioId())
    .map(data -> new ObjectItem(
        data.nameObject,
        data.description,
        data.scenarioId,
        data.path
    ))
    .toList();

scenario.setObjectScenarioList(items);
```

👉 Agora o cenário recebe os itens vindos dos dados

---

### 5) Como pegar um item depois

Seu método atual continua válido:

```java
ObjectItem item = findObjectById(44, "Sword");
```

👉 A diferença: o item veio do JSON, não do código

---

## 🎮 Fluxo completo

1. Criar JSON
2. Repository lê os JSONs
3. JSON vira ObjectItemData
4. ObjectItemData vira ObjectItem
5. Scenario recebe os ObjectItem
6. Jogo usa normalmente

---

## 🧠 Regra importante

- Nunca criar item direto no código
- Sempre carregar do repository
- Sempre trabalhar com ObjectItem (modelo final)

---

## 🚀 Benefícios

- Fácil adicionar novos itens
- Sem duplicação
- Código organizado
- Separação clara de responsabilidade
- Escalável
