import "./App.css";
import {
  Container,
  NumberInput,
  Group,
  TextInput,
  Button,
  Table,
  Loader,
  Alert,
  Switch,
} from "@mantine/core";
import { useState } from "react";

function App() {
  const [id, setID] = useState(undefined);
  const [name, setName] = useState("");
  const [price, setPrice] = useState(undefined);
  const [quantity, setQuantity] = useState(undefined);
  const [inSale, setInSale] = useState(undefined);
  const [links, setLinks] = useState(null);
  const [data, setData] = useState([]);
  const [errorText, setErrorText] = useState(null);

  const linkTypes = [
    {
      name: "delete",
      method: "DELETE",
    },
    {
      name: "add quantity",
      method: "GET",
    },
    {
      name: "sell",
      method: "GET",
    },
    {
      name: "list all",
      method: "GET",
    },
  ];

  const onClickLoadList = () => {
    fetch(`http://localhost:8080/products`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (data.detail) {
          setErrorText(data.detail);
        } else {
          setErrorText(null);
          setData(data._embedded.productList);
        }
      })
      .catch((error) => {
        setErrorText(error);
      });
  };

  const onClickLoad = () => {
    fetch(`http://localhost:8080/products/${id}`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data._links);
        if (data.detail) {
          setErrorText(data.detail);
        } else {
          setErrorText(null);
          setName(data.name);
          setPrice(data.price);
          setQuantity(data.quantity);
          setInSale(data.inSale);
          setLinks(data._links);
        }
      })
      .catch((error) => {
        setErrorText(error);
      });
  };

  const onClickUpdate = () => {
    fetch(`http://localhost:8080/products`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id,
        name,
        quantity,
        price,
        inSale,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.detail) {
          setErrorText(data.detail);
        } else {
          setErrorText(null);
          onClickLoadList();
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const onClickDelete = () => {
    fetch(`http://localhost:8080/products/${id}`, {
      method: "DELETE",
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.detail) {
          setErrorText(data.detail);
        } else {
          setErrorText(null);
          onClickLoadList();
        }
      })
      .catch((error) => {
        setErrorText(error);
      });
  };

  const onClickAdd = () => {
    fetch(`http://localhost:8080/products`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers":
          "Origin, X-Requested-With, Content-Type, Accept",
      },
      body: JSON.stringify({
        name,
        quantity,
        price,
        inSale,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.detail) {
          setErrorText(data.detail);
        } else {
          setErrorText(null);
          onClickLoadList();
        }
      })
      .catch((error) => {
        setErrorText(error);
      });
  };

  const onClickLinks = (url, method) => {
    fetch(url, {
      method: method,
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.detail) {
          setErrorText(data.detail);
        } else {
          onClickLoadList();
        }
      });
  };

  return (
    <Container style={{ padding: "50px" }}>
      <Group style={{ paddingBottom: "50px" }}>
        <NumberInput
          value={id}
          onChange={(value) => setID(value)}
          label="Person ID"
          placeholder="Enter ID"
        />
        <TextInput
          value={name}
          onChange={(event) => setName(event.currentTarget.value)}
          label="Name"
          placeholder="Enter name"
        />
        <NumberInput
          value={price}
          onChange={(value) => setPrice(value)}
          precision={2}
          step={0.01}
          label="Price"
          placeholder="Enter price"
        />
        <NumberInput
          value={quantity}
          onChange={(value) => setQuantity(value)}
          label="Quantity"
          placeholder="Enter quantity"
        />
        <Switch
          label="In Sale"
          checked={inSale}
          onChange={(event) => setInSale(event.currentTarget.checked)}
          disabled={inSale === undefined}
        />
      </Group>
      {errorText && (
        <Group style={{ paddingBottom: "50px" }}>
          <Alert
            title={errorText}
            color="red"
            withCloseButton
            onClose={() => setErrorText(null)}
          ></Alert>
        </Group>
      )}
      <Group style={{ paddingBottom: "50px" }}>
        <Button onClick={onClickLoad}>Wczytaj</Button>
        <Button onClick={onClickUpdate}>Aktualizuj</Button>
        <Button onClick={onClickAdd}>Dodaj</Button>
        <Button onClick={onClickDelete}>Usun</Button>
        <Button onClick={onClickLoadList}>Wyswietl liste</Button>
      </Group>
      <Group style={{ paddingBottom: "50px" }}>
        {links &&
          linkTypes.map((linkType) => {
            if (links.hasOwnProperty(linkType.name)) {
              return (
                <Button
                  key={links[linkType.name].href}
                  onClick={() =>
                    onClickLinks(links[linkType.name].href, linkType.method)
                  }
                >
                  {linkType.name} - {links[linkType.name].href}
                </Button>
              );
            }
            return <></>;
          })}
      </Group>
      <Table>
        <thead>
          <tr>
            <th>Product ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>In Sale</th>
          </tr>
        </thead>
        <tbody>
          {data.map((product) => (
            <tr key={product.id}>
              <td>{product.id}</td>
              <td>{product.name}</td>
              <td>{product.price}</td>
              <td>{product.quantity}</td>
              <td>{product.inSale === true ? "true" : "false"}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}

export default App;
