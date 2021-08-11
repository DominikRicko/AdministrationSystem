declare namespace kendo.data{

}

declare namespace kendo.ui{
	interface GridOptions {
        name?: string;
        allowCopy?: boolean|GridAllowCopy;
        altRowTemplate?: string|Function;
        autoBind?: boolean;
        columnResizeHandleWidth?: number;
        columns?: GridColumn[];
        columnMenu?: boolean|GridColumnMenu;
        dataSource?: kendo.data.DataSourceOptions;
        detailTemplate?: string|Function;
        editable?: boolean|GridEditable;
        excel?: GridExcel;
        filterable?: boolean|GridFilterable;
        groupable?: boolean|GridGroupable;
        height?: number|string;
        messages?: GridMessages;
        mobile?: boolean|string;
        navigatable?: boolean;
        noRecords?: boolean|GridNoRecords;
        pageable?: boolean|GridPageable;
        pdf?: GridPdf;
        reorderable?: boolean;
        resizable?: boolean;
        rowTemplate?: string|Function;
        scrollable?: boolean|GridScrollable;
        selectable?: boolean|string;
        sortable?: boolean|GridSortable;
        toolbar?: GridToolbarItem[] | any;
        cancel?(e: GridCancelEvent): void;
        change?(e: GridChangeEvent): void;
        columnHide?(e: GridColumnHideEvent): void;
        columnMenuInit?(e: GridColumnMenuInitEvent): void;
        columnReorder?(e: GridColumnReorderEvent): void;
        columnResize?(e: GridColumnResizeEvent): void;
        columnShow?(e: GridColumnShowEvent): void;
        dataBinding?(e: GridDataBindingEvent): void;
        dataBound?(e: GridDataBoundEvent): void;
        detailCollapse?(e: GridDetailCollapseEvent): void;
        detailExpand?(e: GridDetailExpandEvent): void;
        detailInit?(e: GridDetailInitEvent): void;
        edit?(e: GridEditEvent): void;
        excelExport?(e: GridExcelExportEvent): void;
        filter?(e: GridFilterEvent): void;
        group?(e: GridGroupEvent): void;
        page?(e: GridPageEvent): void;
        pdfExport?(e: GridPdfExportEvent): void;
        filterMenuInit?(e: GridFilterMenuInitEvent): void;
        remove?(e: GridRemoveEvent): void;
        save?(e: GridSaveEvent): void;
        saveChanges?(e: GridSaveChangesEvent): void;
        sort?(e: GridSortEvent): void;
        columnLock?(e: GridColumnLockEvent): void;
        columnUnlock?(e: GridColumnUnlockEvent): void;
        navigate?(e: GridNavigateEvent): void;
    }
	
}