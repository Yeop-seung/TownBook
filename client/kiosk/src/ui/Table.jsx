import React from "react";
import { useGlobalFilter, useSortBy, useTable } from "react-table";
import Search from "./Search";

function Table({ columns, data }) {
    const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow, setGlobalFilter } =
        useTable({ columns, data }, useGlobalFilter, useSortBy);

    return (
        <>
            <Search onSubmit={setGlobalFilter}/>
            <table {...getTableProps()}>
            <thead>
                {headerGroups.map((headerGroup) => (
                <tr {...headerGroup.getHeaderGroupProps()}>
                    {headerGroup.headers.map((column) => (
                    <th {...column.getHeaderProps(column.getSortByToggleProps())}>{column.render("Header")}</th>
                    ))}
                </tr>
                ))}
            </thead>
            <tbody {...getTableBodyProps()}>
                {rows.map((row) => {
                prepareRow(row);
                return (
                    <tr {...row.getRowProps()}>
                    {row.cells.map((cell) => (
                        <td {...cell.getCellProps()}>{cell.render("Cell")}</td>
                    ))}
                    </tr>
                );
                })}
            </tbody>
            </table>
    </>
    );
    }

export default Table;