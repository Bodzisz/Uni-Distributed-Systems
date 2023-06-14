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
} from "@mantine/core";
import { useState } from "react";

function App() {
  const [id, setID] = useState(undefined);
  const [name, setName] = useState("");
  const [age, setAge] = useState(undefined);
  const [data, setData] = useState([]);
  const [errorText, setErrorText] = useState(null);

  const onClickLoadList = () => {
    fetch(`http://localhost:8080/persons`)
      .then((response) => response.json())
      .then((data) => {
        if (data.detail) {
          setErrorText(data.detail);
        } else {
          setErrorText(null);
          setData(data);
        }
      })
      .catch((error) => {
        setErrorText(error);
      });
  };

  const onClickLoad = () => {
    fetch(`http://localhost:8080/persons/${id}`)
      .then((response) => response.json())
      .then((data) => {
        if (data.detail) {
          setErrorText(data.detail);
        } else {
          setErrorText(null);
          setName(data.name);
          setAge(data.age);
        }
      })
      .catch((error) => {
        setErrorText(error);
      });
  };

  const onClickUpdate = () => {
    fetch(`http://localhost:8080/persons`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id,
        name,
        age,
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
    fetch(`http://localhost:8080/persons/${id}`, {
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
    fetch(`http://localhost:8080/persons`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id,
        name,
        age,
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
          value={age}
          onChange={(value) => setAge(value)}
          label="Age"
          placeholder="Enter age"
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
      <Table>
        <thead>
          <tr>
            <th>Person ID</th>
            <th>Name</th>
            <th>Age</th>
          </tr>
        </thead>
        <tbody>
          {data.map((person) => (
            <tr key={person.id}>
              <td>{person.id}</td>
              <td>{person.name}</td>
              <td>{person.age}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}

export default App;
