import {
  Paper,
  Table,
  TableContainer,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  styled as styledd,
  tableCellClasses,
  TextField,
  Button,
  IconButton,
} from "@mui/material";
import {
  MdDelete,
  MdOutlineSave,
  MdOutlineCheck,
  MdOutlineClose,
} from "react-icons/md";
import { useEffect, useState } from "react";
import styled from "styled-components";
import AddModal from "./AddModal";

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const StyledTableCell = styledd(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styledd(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

const Container = styled.div`
  padding: 30px 130px;
  display: flex;
  flex-direction: column;
`;

const Zaposlenici = () => {
  const [items, setItems] = useState([]);
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [ime, setIme] = useState();
  const [prezime, setPrezime] = useState();
  const [korIme, setKorIme] = useState();
  const [changed, setChanged] = useState();
  const [open, setOpen] = useState(false);

  const handleDelete = (uuid) => {
    fetch("http://localhost:8080/api/v1/zaposlenici/" + uuid, {
      method: "DELETE",
    }).then(window.location.reload());
  };

  const handleSave = (data) => {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        uuid: data.uuid,
        ime: ime || data.ime,
        prezime: prezime || data.prezime,
        isVoditelj: data.isVoditelj,
        korisnickoIme: korIme || data.korisnickoIme,
        mjesto: data.mjesto,
      }),
    };
    fetch("http://localhost:8080/api/v1/zaposlenici/update", requestOptions)
      .then((response) => response.json())
      .then(window.location.reload());
  };

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/zaposlenici")
      .then((res) => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setItems(result);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
  }, []);

  if (isLoaded) {
    return (
      <Container>
        <div style={{ textAlign: "right", width: "100%" }}>
          <Button
            color="secondary"
            variant="contained"
            style={{ marginLeft: "16px" }}
            onClick={() => setOpen(true)}
          >
            Dodaj novog zaposlenika
          </Button>
        </div>
        <TableContainer component={Paper} style={{ marginTop: "16px" }}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <StyledTableCell>Ime</StyledTableCell>
                <StyledTableCell>Prezime</StyledTableCell>
                <StyledTableCell>Korisničko ime</StyledTableCell>
                <StyledTableCell>Mjesto</StyledTableCell>
                <StyledTableCell>Voditelj</StyledTableCell>
                <StyledTableCell></StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {items.map((row, index) => (
                <StyledTableRow
                  key={row.uuid}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                >
                  <StyledTableCell>
                    <TextField
                      label=""
                      variant="standard"
                      value={changed === index ? ime || row.ime : row.ime}
                      onChange={(e) => {
                        setIme(e.target.value);
                        setChanged(index);
                      }}
                      fullWidth
                    />
                  </StyledTableCell>
                  <StyledTableCell>
                    <TextField
                      label=""
                      variant="standard"
                      value={
                        changed === index ? prezime || row.prezime : row.prezime
                      }
                      onChange={(e) => {
                        setPrezime(e.target.value);
                        setChanged(index);
                      }}
                      fullWidth
                    />
                  </StyledTableCell>
                  <StyledTableCell>
                    <TextField
                      label=""
                      variant="standard"
                      value={
                        changed === index
                          ? korIme || row.korisnickoIme
                          : row.korisnickoIme
                      }
                      onChange={(e) => {
                        setKorIme(e.target.value);
                        setChanged(index);
                      }}
                      fullWidth
                    />
                  </StyledTableCell>
                  <StyledTableCell>{row.mjesto.nazivMjesta}</StyledTableCell>
                  <StyledTableCell align="center">
                    {row.isVoditelj ? <MdOutlineCheck /> : <MdOutlineClose />}
                  </StyledTableCell>
                  <StyledTableCell align="right">
                    <ButtonContainer>
                      <IconButton
                        color="error"
                        aria-label="delete"
                        onClick={() => handleDelete(row.uuid)}
                      >
                        <MdDelete />
                      </IconButton>
                      <IconButton
                        disabled={changed !== index}
                        color="success"
                        aria-label="edit"
                        onClick={() => {
                          handleSave(row);
                        }}
                      >
                        <MdOutlineSave />
                      </IconButton>
                    </ButtonContainer>
                  </StyledTableCell>
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        {open && <AddModal open={open} setOpen={setOpen} />}
      </Container>
    );
  }
};

export default Zaposlenici;
